package Tests;

public class Servis {

    public static void main(String[] args) {

        Okul okulObje = new Okul();
        System.out.println(okulObje.okulIsmi);
        okulObje.okulMethod();

        System.out.println(Okul.ogrenciSayisi);
    }
}
