package servlet.part;

public class RemainderMethod {
    public static int getRemainder(int d, int all) {
        //three case 1sh >  2nd <  3rd ==
        int result = 0;
        if (d > all || d == all) {
            result = 1;
        } else if (d < all) {
            result = all % d;
            if (result == 0 && d != 1) {
                result = all/d;
            } else if (d == 1) {
                result = all;
            } else {
                result = (all / d) + 1;
            }
        }
        return result;
    }
}
