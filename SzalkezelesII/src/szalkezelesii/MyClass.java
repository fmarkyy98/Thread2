package szalkezelesii;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MyClass {

	private final Random random = new Random ();

	private final ExecutorService executor = Executors.newCachedThreadPool ();

	private class Feladat1 implements Callable<Double> {
		@Override
		public Double call () {
			System.err.println ("F1 start...");
			try { Thread.sleep (5000); }
			catch (final InterruptedException ignore) {}
			System.err.println ("F1 finish...");
			return random.nextDouble () * 5.56;
		}
	}

	private class Feladat2 implements Callable<Double> {
		@Override
		public Double call () {
			System.err.println ("F2 start...");
			try { Thread.sleep (2000); }
			catch (final InterruptedException ignore) {}
			System.err.println ("F2 finish...");
			return random.nextDouble () * 4.89;
		}
	}

	public void futtat () throws Exception {
		final Future<Double> f1 = executor.submit (new Feladat1 ());
		final Future<Double> f2 = executor.submit (new Feladat2 ());

		System.err.println("Amíg számolódik az eredmény csinálhatunk mást is...");

		final double eredm = f1.get () + f2.get ();
		System.err.println ("Eredmény: " + eredm);

		executor.shutdown ();
	}
}
