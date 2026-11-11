# Git: практикум із гілками, PR та виправленням комітів

Мета: пройти типовий цикл роботи розробника, навмисно створити проблеми та навчитися їх виправляти. Працюйте послідовно: наступні вправи використовують результати попередніх.

## 0. Підготовка

Потрібні Git, текстовий редактор та обліковий запис GitHub. Команди нижче призначені для Bash: Linux, macOS або **Git Bash на Windows**, а не PowerShell. Усі файли можна редагувати через IDE.

Виконуйте команди лише в окремому навчальному репозиторії. Перед перемиканням гілки перевіряйте `git status`: якщо є зміни, закомітьте їх або тимчасово відкладіть через `git stash push -m "Work in progress"`; повернути їх можна через `git stash pop`.

Перевірте Git:

```bash
git --version
mkdir git-playground
cd git-playground
git init -b main
git config user.name "Student Name"
git config user.email "student@example.com"
```

Замість навчальних імені та email введіть власні. Налаштування діють лише для цього репозиторію. Якщо старий Git не підтримує `init -b`, виконайте `git init`, потім `git branch -M main`.

Створіть початкові файли:

```bash
echo "# Git Playground" > README.md
echo "Welcome to our course" > greeting.txt
echo "discount=10" > pricing.txt
git add README.md greeting.txt pricing.txt
git diff --cached
git commit -m "Create initial project"
git tag lab-start
git status
```

**Очікування:** один коміт, гілка `main`, чиста робоча директорія.

### Терміни, які потрібні для практики

| Термін | Значення |
| --- | --- |
| Working tree | Файли, з якими ви зараз працюєте |
| Index / staging area | Підготовлений вміст наступного коміту |
| Commit | Знімок стану проєкту з автором, повідомленням і посиланням на батьківські коміти |
| Branch | Рухомий покажчик на коміт; дозволяє розвивати окрему лінію змін |
| HEAD | Зазвичай посилається на поточну гілку; у detached HEAD — прямо на коміт |
| main / master | Звичайні назви основної гілки. Особливих прав у Git не мають; default branch визначає хостинг |
| origin | Умовна назва віддаленого репозиторію |
| origin/main | Локальне уявлення про стан віддаленої гілки після останнього fetch |
| Fork | Окремий репозиторій на хостингу, створений з іншого репозиторію |
| Pull Request | Запит на перевірку та інтеграцію змін на GitHub; це функція платформи |

## 1. Створення гілки та git checkout

**Ситуація:** треба додати опис курсу, не змінюючи main.

```bash
git branch
git checkout -b feature/course-description
echo "This repository is used to practice Git." >> README.md
git diff
git add README.md
git commit -m "Add course description"
git checkout main
cat README.md
git checkout feature/course-description
cat README.md
```

**Очікування:** доданий рядок є у feature-гілці, але відсутній у main.

`git branch NAME` лише створює гілку. `git checkout NAME` перемикає на неї. `git checkout -b NAME` робить обидві дії. Сучасні альтернативи: `git switch NAME` та `git switch -c NAME`. Checkout також уміє відновлювати файли, тому уважно дивіться на аргументи.

**Завдання:** поверніться в main, створіть `feature/student-notes`, додайте файл `notes.txt` з одним рядком, зробіть коміт. Поверніться в main.

**Питання:** чи потрапили зміни першої feature-гілки в другу? Чому важливо перевіряти поточну гілку перед створенням нової?

## 2. Git Log: читаємо історію

```bash
git log --oneline
git log --oneline --graph --decorate --all
git log --oneline main..feature/course-description
git diff main...feature/course-description
git show feature/course-description
```

**Очікування:** у графі видно дві feature-гілки від початкового коміту. Запис `main..feature/course-description` показує коміти, доступні з feature-гілки, але недоступні з main. Три крапки в diff порівнюють feature-гілку зі спільним предком.

Git Log показує історію комітів. Він не показує незакомічені зміни — для них потрібні status і diff. Якщо відкрився переглядач, натисніть `q`.

**Завдання:** знайдіть автора, дату та змінені рядки одного коміту. Для конкретного коміту використайте `git show HASH`, замінивши HASH його ідентифікатором із log.

## 3. Merge без конфлікту

**Ситуація:** обидва завдання готові, їх треба додати в main.

```bash
git checkout main
git merge feature/course-description
git merge --no-ff feature/student-notes -m "Merge student notes"
git log --oneline --graph --decorate --all
git status
```

**Очікування:** main містить опис і notes.txt. Перше злиття — fast-forward: покажчик main перемістився вперед без нового merge-коміту. Друге злиття створило merge-коміт з двома батьками.

Merge інтегрує історії гілок. Якщо одна гілка є прямим продовженням іншої, можливий fast-forward. Коли історії розійшлися, Git зазвичай створює merge-коміт.

**Завдання:** подивіться батьків останнього коміту через `git show --no-patch --format=raw HEAD`. Поясніть, чому їх два.

## 4. Навмисно створюємо Merge Conflict

**Ситуація:** двоє розробників змінюють той самий рядок по-різному.

```bash
git checkout main
git checkout -b feature/friendly-greeting
echo "Hello, students!" > greeting.txt
git add greeting.txt
git commit -m "Make greeting friendly"

git checkout main
git checkout -b feature/formal-greeting
echo "Welcome, participants!" > greeting.txt
git add greeting.txt
git commit -m "Make greeting formal"

git checkout main
git merge feature/friendly-greeting
git merge feature/formal-greeting
git status
cat greeting.txt
```

**Очікування:** останній merge зупиниться з конфліктом. Це запланований результат.

У файлі з'являться маркери:

```text
<<<<<<< HEAD
Hello, students!
=======
Welcome, participants!
>>>>>>> feature/formal-greeting
```

HEAD тут — поточний main, нижня частина — зміна з гілки, яку інтегруємо. Git не може сам визначити правильний зміст. Конфлікти також виникають при видаленні, перейменуванні файлів, cherry-pick та інших операціях.

### Розв'язання

1. Відкрийте greeting.txt.
2. Замініть весь блок, включно з маркерами, одним узгодженим рядком: `Hello, students! Welcome to the course.`
3. Завершіть злиття:

```bash
git diff
git add greeting.txt
git diff --cached
git commit -m "Merge greetings and resolve conflict"
git status
git log --oneline --graph --decorate --all
```

**Очікування:** чиста робоча директорія, у файлі немає маркерів конфлікту.

Якщо хочете скасувати незавершене злиття, використайте `git merge --abort` **до фінального коміту**, потім повторіть merge. У цій вправі операція починалася з чистої директорії.

**Завдання в парі:** обґрунтуйте фінальний текст. Механічне «Accept ours/theirs» без перевірки може видалити потрібні зміни.

## 5. GitHub: створення Pull Request і Code Review

**Ситуація:** зміни мають пройти перевірку колегою.

1. На GitHub створіть власний порожній репозиторій `git-playground`. Не додавайте README, license чи .gitignore: локальний репозиторій уже містить коміти.
2. Скопіюйте HTTPS або SSH URL. У команді нижче замініть YOUR_USERNAME власним login; для SSH використайте URL, запропонований GitHub.

```bash
git remote add origin https://github.com/YOUR_USERNAME/git-playground.git
git push -u origin main
git checkout -b feature/review-practice
echo "Code review helps us improve changes before merging." >> README.md
git add README.md
git commit -m "Explain code review"
git push -u origin feature/review-practice
```

Для HTTPS використовуйте підтримувану GitHub авторизацію, наприклад credential manager або token; звичайний пароль облікового запису не підходить. Не вставляйте token у команди чи файли проєкту.

3. На GitHub відкрийте **Pull requests → New pull request**.
4. Оберіть **base: main**, **compare: feature/review-practice**.
5. Перевірте diff; створіть PR із назвою `Explain code review`.
6. В описі вкажіть: що змінилося, навіщо та як перевірено.
7. Попросіть іншого студента перевірити PR. Для review в одному репозиторії забезпечте потрібний доступ.
8. Рецензент відкриває **Files changed**, залишає конкретний коментар, наприклад: «Додай один приклад перевірки під час review».
9. Автор додає приклад у README.md, робить коміт і push у ту саму гілку:

```bash
echo "Review example: check whether a change breaks existing behavior." >> README.md
git add README.md
git commit -m "Add review example"
git push
```

10. Переконайтеся, що PR оновився автоматично. Рецензент перевіряє виправлення та обирає Approve. Автор не може схвалити власний PR.
11. Злийте PR. Для цього практикуму оберіть **Create a merge commit**, якщо опція доступна. Squash і rebase змінюють форму історії; до них можна повернутися окремо.

**Code Review** — перевірка змін іншою людиною: правильність поведінки, читабельність, тести, побічні ефекти. Коментар має пояснювати проблему й очікуваний результат.

**Самостійний варіант:** залиште коментар до свого PR і додайте виправлення. Справжнє незалежне review та Approve потребують іншого учасника.

**Очікування:** PR злитий на GitHub, але локальний main поки не оновлений. Не виконуйте pull — це частина наступної вправи.

## 6. Git Fetch: отримати зміни без злиття

```bash
git checkout main
git log -1 --oneline main
git fetch origin
git log -1 --oneline main
git log -1 --oneline origin/main
git log --oneline main..origin/main
git diff main origin/main
```

**Очікування:** fetch оновив origin/main, але не перемістив локальний main і не змінив ваші робочі файли.

Тепер свідомо оновіть main:

```bash
git merge --ff-only origin/main
git status
```

**Очікування:** локальний main відповідає origin/main. Якщо є власні розбіжні коміти, --ff-only відмовиться працювати: спочатку з'ясуйте причину через граф історії.

Fetch завантажує об'єкти та оновлює віддалені покажчики. Pull виконує fetch, а потім інтеграцію — merge або rebase залежно від параметрів і налаштувань.

**Завдання:** через GitHub змініть README.md у main та закомітьте. Повторіть fetch, порівняння і merge --ff-only. До merge перевірте, що локальний файл ще не змінився.

## 7. Fork і Branch: практика з чужим репозиторієм

| | Branch | Fork |
| --- | --- | --- |
| Де існує | Усередині репозиторію | Окремий репозиторій на хостингу |
| Призначення | Окрема лінія роботи | Власний репозиторій для внеску в інший проєкт |
| Доступ | Для push у спільний remote потрібні права | Можна push у власний fork без прав на оригінал |
| Чи може містити гілки | Це сама гілка | Так, fork містить гілки |

**Ситуація:** треба запропонувати зміни викладачу, не маючи прав push у його репозиторій.

1. Викладач надає URL навчального репозиторію на GitHub. Оберіть **Fork** і створіть копію у своєму обліковому записі.
2. Вийдіть із попереднього каталогу через `cd ..`.
3. Замініть YOUR_USERNAME, TEACHER та training-repo реальними значеннями:

```bash
git clone https://github.com/YOUR_USERNAME/training-repo.git fork-playground
cd fork-playground
git remote add upstream https://github.com/TEACHER/training-repo.git
git remote -v
git checkout -b feature/student-contribution
```

4. Додайте файл `student-contribution.txt` зі своїм ім'ям та одним висновком про Git:

```bash
git add student-contribution.txt
git commit -m "Add student contribution"
git push -u origin feature/student-contribution
```

5. Створіть PR: **base repository — репозиторій викладача**, base — його основна гілка; **head repository — ваш fork**, compare — feature/student-contribution. Якщо основна гілка називається master, використовуйте master.
6. Отримайте актуальну історію оригіналу через `git fetch upstream`. Це ще не оновлює вашу feature-гілку.

**Очікування:** origin веде до вашого fork, upstream — до оригіналу, PR пропонує зміни в оригінал.

Для наступних вправ поверніться: `cd ../git-playground`. Використовуйте тільки власний навчальний репозиторій.

## 8. Cherry-pick: перенесення одного коміту

**Ситуація:** гілка містить нову функцію й окреме виправлення. У main потрібне лише виправлення.

```bash
git checkout main
git checkout -b feature/large-change
echo "Experimental feature" > experiment.txt
git add experiment.txt
git commit -m "Add experimental feature"
echo "Git exercise troubleshooting notes" > troubleshooting.txt
git add troubleshooting.txt
git commit -m "Add troubleshooting notes"
git log --oneline -2
git checkout main
git cherry-pick feature/large-change
git log --oneline -3
```

Тут ім'я гілки вказує на її останній коміт. Загальна форма: `git cherry-pick HASH`.

**Очікування:** troubleshooting.txt є у main, experiment.txt відсутній. Cherry-pick переніс зміни одного коміту; зазвичай створюється новий коміт з іншим hash.

При конфлікті відредагуйте файли, виконайте `git add FILE`, потім `git cherry-pick --continue`. Для скасування незавершеної операції — `git cherry-pick --abort`.

**Завдання:** порівняйте коміти через log --all. Чому cherry-pick не дорівнює merge усієї гілки?

## 9. Виправлення останнього локального коміту: amend

**Ситуація:** забули рядок і зробили помилку в повідомленні. Коміт ще не відправляли.

```bash
git checkout -b lab/amend
echo "First note" > amend-demo.txt
git add amend-demo.txt
git commit -m "Add ntoe"
git log -1 --oneline
echo "Second note" >> amend-demo.txt
git add amend-demo.txt
git commit --amend -m "Add complete notes"
git log -1 --oneline
git show HEAD
```

**Очікування:** один новий коміт містить обидва рядки; його hash змінився. Amend замінює останній коміт новим. Лише для зміни повідомлення можна виконати amend -m без додавання файлів.

Опубліковані спільні коміти в цьому практикумі виправляйте новими комітами, не amend.

## 10. Git Reset: три режими на однаковому прикладі

**Ситуація:** локальний коміт зробили завчасно. Треба зрозуміти, що залишиться після reset.

Підготуйте знімок:

```bash
git checkout main
git checkout -b lab/reset-source
echo "Temporary local change" > reset-demo.txt
git add reset-demo.txt
git commit -m "Add temporary reset demo"
git branch lab/reset-soft
git branch lab/reset-mixed
git branch lab/reset-hard
```

Три гілки вказують на той самий коміт. lab/reset-source зберігає резервне посилання.

### A. --soft: скасувати коміт, залишити staged-зміни

```bash
git checkout lab/reset-soft
git reset --soft HEAD~1
git status
git diff --cached
git commit -m "Recreate commit after soft reset"
```

**Очікування:** перед повторним commit файл присутній і підготовлений до коміту.

### B. --mixed: скасувати коміт і staging, залишити файли

```bash
git checkout lab/reset-mixed
git reset --mixed HEAD~1
git status
cat reset-demo.txt
git add reset-demo.txt
git commit -m "Recreate commit after mixed reset"
```

**Очікування:** перед git add новий reset-demo.txt відображається як untracked, бо його не було у попередньому коміті. Зміни вже відстежуваних файлів були б unstaged. Mixed — типовий режим reset.

### C. --hard: повернути також файли до попереднього коміту

Спочатку перевірте поточну гілку та чистий status. Тут навмисно видаляється лише навчальний файл; резервна гілка вже створена.

```bash
git checkout lab/reset-hard
git status
git reset --hard HEAD~1
git status
git ls-files reset-demo.txt
git log --oneline -2
```

**Очікування:** reset-demo.txt зник із цієї робочої копії. Коміт залишається доступним через lab/reset-source. Hard також відкидає незакомічені зміни відстежуваних файлів і може перезаписати untracked-файли, які заважають відновленню.

| Режим | Поточна гілка | Index | Working tree |
| --- | --- | --- | --- |
| --soft | Переміщується на цільовий коміт | Не змінюється | Не змінюється |
| --mixed | Переміщується | Відповідає цільовому коміту | Не змінюється |
| --hard | Переміщується | Відповідає цільовому коміту | Відповідає цільовому коміту |

HEAD~1 — перший батько поточного коміту. Reset переміщує гілку; це не гарантує фізичного видалення коміту з Git. Не використовуйте reset для переписування спільної опублікованої історії.

## 11. Git Revert: скасування вже опублікованого коміту

**Ситуація:** помилкову знижку вже відправили в remote.

```bash
git checkout main
git checkout -b lab/revert
echo "discount=90" > pricing.txt
git add pricing.txt
git commit -m "Set incorrect discount"
git push -u origin lab/revert
git log -1 --oneline
git revert --no-edit HEAD
cat pricing.txt
git log --oneline -3
git push
```

**Очікування:** discount=10 відновлено, у log є помилковий коміт і новий коміт, який скасовує його зміни.

Revert зберігає історію, додаючи протилежну зміну. Reset переміщує гілку назад. Для спільної опублікованої історії зазвичай обирайте revert. Якщо вже існують залежні зміни, revert може конфліктувати: розв'яжіть конфлікт, git add, git revert --continue; або git revert --abort.

**Завдання:** поясніть, чому в цій ситуації новий виправний коміт або revert доречніший за reset і force push.

## 12. Пошук зламаного коміту: log, show та bisect

**Ситуація:** спочатку знижка була правильною, потім хтось зламав конфігурацію.

```bash
git checkout main
git checkout -b lab/debug
git tag lab-debug-good
echo "Step 1" > debug-notes.txt
git add debug-notes.txt
git commit -m "Add debug notes"
echo "Step 2" >> debug-notes.txt
git add debug-notes.txt
git commit -m "Extend debug notes"
echo "discount=90" > pricing.txt
git add pricing.txt
git commit -m "Accidentally change discount"
echo "Step 3" >> debug-notes.txt
git add debug-notes.txt
git commit -m "Add more debug notes"
```

Спочатку дослідіть:

```bash
git log --oneline -- pricing.txt
git show HEAD~1 -- pricing.txt
```

Правило перевірки: discount=10 — good, інше значення — bad.

```bash
git bisect start
git bisect bad
git bisect good lab-debug-good
cat pricing.txt
```

Git вибере проміжний коміт. Після кожної перевірки введіть **одну** команду: git bisect good або git bisect bad залежно від значення. Повторюйте cat pricing.txt та позначення, поки Git не повідомить перший поганий коміт.

1. Запишіть його hash.
2. Поверніться на початкову гілку: `git bisect reset`.
3. Виконайте `git revert --no-edit HASH`, підставивши знайдений hash.
4. Перевірте pricing.txt і git log.

**Очікування:** знайдено «Accidentally change discount», знижка відновлена, пізніші зміни debug-notes.txt збережені. Bisect тимчасово перемикає робочу копію між комітами, тому починайте з чистого status.

## 13. Відновлення коміту після випадкового reset

**Ситуація:** локальний коміт більше не видно у звичайному log.

```bash
git checkout main
git checkout -b lab/recovery
echo "Important learning note" > recovery.txt
git add recovery.txt
git commit -m "Add recovery note"
git status
git reset --hard HEAD~1
git log --oneline -2
git reflog -5
```

У reflog знайдіть запис `commit: Add recovery note`, скопіюйте його hash та виконайте:

```bash
git branch recovered-note HASH
git show recovered-note:recovery.txt
git checkout recovered-note
git status
```

**Очікування:** файл відновлено через нову гілку. Reflog — локальний журнал переміщень покажчиків, не історія комітів проєкту. Він має обмежений термін зберігання і не відновлює довільні незакомічені зміни, втрачені після hard reset.

## 14. Підсумкова командна задача

Об'єднайтесь у пари; працюйте у навчальному репозиторії.

1. Отримайте актуальний main через fetch і merge --ff-only.
2. Кожен створює свою гілку від одного main.
3. Обидва змінюють один і той самий рядок greeting.txt, комітять і відкривають PR.
4. Перевірте й злийте перший PR.
5. Другий автор виконує git fetch origin, потім у власній feature-гілці git merge origin/main.
6. Розв'яжіть конфлікт, перевірте результат, зробіть commit і push. Перевірте оновлений PR та злийте.
7. В окремій гілці створіть два незалежні коміти. Перенесіть лише другий у нову гілку через cherry-pick і запропонуйте його PR.
8. У власній lab-гілці опублікуйте помилку та скасуйте її через revert.

**Критерії завершення:**

- Є PR з коментарем і виправленням після review.
- Є розв'язаний конфлікт без втрати узгоджених змін.
- Студент пояснює відмінність main, origin/main, branch і fork.
- Студент демонструє, що fetch сам не змінює локальний main.
- Студент показує cherry-pick одного коміту, три режими reset і revert.
- Студент знаходить поганий коміт і відновлює загублений через reflog.

## Шпаргалка: яку дію обрати?

| Потреба | Дія |
| --- | --- |
| Нова задача | git checkout -b feature/name |
| Подивитися історію | git log --oneline --graph --decorate --all |
| Завантажити remote-зміни без інтеграції | git fetch origin |
| Інтегрувати гілку в поточну | git merge BRANCH |
| Перенести один коміт | git cherry-pick HASH |
| Виправити останній неопублікований коміт | git commit --amend |
| Забрати файл зі staging без втрати робочих змін | git restore --staged FILE |
| Скасувати локальний коміт, залишити staging | git reset --soft HEAD~1 |
| Скасувати локальний коміт, залишити файли | git reset --mixed HEAD~1 |
| Відкинути локальні зміни та повернути знімок | git reset --hard HASH — лише після перевірки й резервування |
| Скасувати зміни опублікованого коміту | git revert HASH |
| Знайти перший поганий коміт | git bisect |
| Знайти локально загублений коміт | git reflog |

## Для викладача

Перше заняття: вправи 0–6. Друге: 7–13. Вправу 14 можна дати як домашню роботу. Перед заняттям перевірте Git Bash, авторизацію GitHub, доступ до навчального репозиторію та можливість merge commit.

Приймайте не лише скриншоти: попросіть студента показати граф історії та пояснити, що сталося з гілкою, staging і файлами. Hash у кожного студента буде свій; оцінюйте стан та поведінку, а не однакові hash.

## Офіційні матеріали

- [Гілки та злиття — Pro Git](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging)
- [git checkout](https://git-scm.com/docs/git-checkout)
- [git log](https://git-scm.com/docs/git-log)
- [git fetch](https://git-scm.com/docs/git-fetch)
- [git cherry-pick](https://git-scm.com/docs/git-cherry-pick)
- [git reset](https://git-scm.com/docs/git-reset)
- [git revert](https://git-scm.com/docs/git-revert)
- [git bisect](https://git-scm.com/docs/git-bisect)
- [Створення PR на GitHub](https://docs.github.com/en/pull-requests/how-tos/create-pull-requests/creating-a-pull-request)
- [Fork на GitHub](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/working-with-forks/fork-a-repo)
