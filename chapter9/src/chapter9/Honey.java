package chapter9;

public class Honey {
    public static void main() {
        Honey honeypot = new Honey();
        Honey[] ha = {honeypot, honeypot, honeypot, honeypot};
        Bees b1 = new Bees();
        b1.beeHa = ha;
        Bear[] ba = new Bear[5];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = new Bear();
            ba[i].honey = honeypot;
        }
        Kit k = new Kit();
        k.kh = honeypot;
        Racoon r = new Racoon();

        r.rh = honeypot;
        r.k = k;
        k = null;
    }
}

class Bees {
    Honey[] beeHa;
}

class Racoon {
    Kit k;
    Honey rh;
}

class Kit {
    Honey kh;
}

class Bear {
    Honey honey;
}
