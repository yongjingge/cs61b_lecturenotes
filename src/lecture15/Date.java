package lecture15;

/* rewrite equals */
public class Date {

    private final int month;
    private final int day;
    private final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Date that = (Date) o;
        if (this.month != that.month || this.day != that.day || this.year != that.year) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Date aa = new Date(12, 11, 1992);
        Date bb = new Date(12, 11, 1992);
        Date cc = new Date(12, 11, 1993);
        Date dd = new Date(12, 11, 1992);
        System.out.println(aa.equals(bb));
        System.out.println(aa.equals(cc));
        System.out.println(aa.equals(null));
        System.out.println(bb.equals(dd));
        System.out.println(aa.equals(dd));
    }
}
