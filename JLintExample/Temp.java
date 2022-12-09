package JLintExample;

public class Temp {

}

class A {
	public synchronized void f1(B b) {
		b.g1();
		f1(b);
		f2(b);
	}

	public void f2(B b) {
		b.g2();
	}

	public static synchronized void f3() {
		B.g3();
	}
}

class B {
	public static volatile A ap = new A();
	public static volatile B bp = new B();

	public synchronized void g1() {
		bp.g1();
	}

	public synchronized void g2() {
		ap.f1(bp);
	}

	public static synchronized void g3() {
		g3();
	}
}