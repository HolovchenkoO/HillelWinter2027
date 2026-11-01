public class ASCIIArtNumbers {
    private static final String[][] DIGITS = {
            {       "  ###  ",
                    " #   # ",
                    "#     #",
                    "#     #",
                    "#     #",
                    " #   # ",
                    "  ###  "
            },
            {       "   #   ",
                    "  ##   ",
                    " # #   ",
                    "   #   ",
                    "   #   ",
                    "   #   ",
                    " ##### "
            },
            {       " ##### ",
                    "#     #",
                    "      #",
                    " ##### ",
                    "#      ",
                    "#      ",
                    "#######"
            },
            {       " ##### ",
                    "#     #",
                    "      #",
                    " ##### ",
                    "      #",
                    "#     #",
                    " ##### "
            },
            {       "#      ",
                    "#    # ",
                    "#    # ",
                    "###### ",
                    "     # ",
                    "     # ",
                    "     # "
            },
            {       "#######",
                    "#      ",
                    "#      ",
                    "###### ",
                    "      #",
                    "#     #",
                    " ##### "
            },
            {       " ##### ",
                    "#     #",
                    "#      ",
                    "###### ",
                    "#     #",
                    "#     #",
                    " ##### "
            },
            {       "#######",
                    "#     #",
                    "      #",
                    "     # ",
                    "    #  ",
                    "   #   ",
                    "   #   "
            },
            {       " ##### ",
                    "#     #",
                    "#     #",
                    " ##### ",
                    "#     #",
                    "#     #",
                    " ##### "
            },
            {       " ##### ",
                    "#     #",
                    "#     #",
                    " ######",
                    "      #",
                    "#     #",
                    " ##### "
            }
    };
    public static boolean isValid(String str) {
        return str != null && str.matches("\\d+");
    }
    public static void drawNumber(String number) {
                    for (int row = 0; row < 7; row++) {
                        for (int i = 0; i < number.length(); i++) {
                             int digit = number.charAt(i) - '0';
                    System.out.print(DIGITS[digit][row] + "  ");
                }
                 System.out.println();
            }
        }
    }

