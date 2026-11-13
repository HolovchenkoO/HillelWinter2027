public class ValueCalculator {
    private final int size = 1000000;
    private final int half = size/2;
    private float [] arr  = new float[size];

    public void calculate () {
        //час старту
        long start = System.currentTimeMillis();

        //заповнення масиву
        for (int i = 0; i < size; i++ ){
            arr[i] = 3.0f;
        }
        //2 масиви в половину розміру

        float[] a1 = new float[half];
        float[] a2 = new float[half];

        // розподіл одного масиву на два
        System.arraycopy(arr, 0, a1, 0, half);
        System.arraycopy(arr, half, a2, 0, half);

        //1 потік
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++){
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
            }
        });

        //2 потік
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < a2.length; i++){
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5.0f) * Math.cos(0.2f + i / 5.0f) * Math.cos(0.4f + i / 2.0f));
            }
        });

        //запуск потоків
        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }
        catch (InterruptedException e) {
            System.err.println("Потік було перервано: " + e.getMessage());
        }
        // Зворотне склеювання
        System.arraycopy(a1, 0, arr, 0, half);
        System.arraycopy(a2, 0, arr, half, half);

        //час виконання
        long end = System.currentTimeMillis();

        System.out.println("Час виконання операції дорівнює : " + (end - start) + " мс.");
    }
}
