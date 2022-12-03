package fr.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		final ExecutorService service = Executors.newFixedThreadPool(5);
		try {
			for (int i = 0; i <= 10; i++) {
				final int j = i;
				service.submit(() -> {
					final int factIter = factIter(j);
					final int factStream = factStream(j);
					synchronized (Main.class) {
						System.out.print("fact(");
						System.out.print(j);
						System.out.print(")= ");
						System.out.print(factIter);
						System.out.print(' ');
						System.out.println(factStream);
					}
				});
			}
			synchronized (Main.class) {
				System.out.println(service);
			}
		} finally {
			service.shutdown();
		}
		synchronized (Main.class) {
			System.out.println(service);
		}
		Thread.sleep(100);
		synchronized (Main.class) {
			System.out.println(service);
		}

		final Path path = Paths.get(".").toAbsolutePath().normalize();
		System.out.println(path);
		Files.walk(path).filter(Files::isRegularFile)
				.forEach(System.out::println);

		new TestConstructor2();

		final Arbre<String> a = new Arbre<>("racine");
		final Arbre<String> ag = new Arbre<>("arbre gauche");
		final Arbre<String> ag1 = new Arbre<>("arbre gauche 1");
		final Arbre<String> ag2 = new Arbre<>("arbre gauche 2");
		final Arbre<String> ad = new Arbre<>("arbre droit");
		final Arbre<String> ad1 = new Arbre<>("arbre droit 1");
		final Arbre<String> ad2 = new Arbre<>("arbre droit 2");
		final Arbre<String> f1 = new Arbre<>("feuille 1");
		final Arbre<String> f2 = new Arbre<>("feuille 2");
		final Arbre<String> f3 = new Arbre<>("feuille 3");
		final Arbre<String> f4 = new Arbre<>("feuille 4");
		final Arbre<String> f5 = new Arbre<>("feuille 5");
		final Arbre<String> f6 = new Arbre<>("feuille 6");
		final Arbre<String> f7 = new Arbre<>("feuille 7");
		final Arbre<String> f8 = new Arbre<>("feuille 8");

		a.setGauche(ag);
		a.setDroit(ad);
		ag.setGauche(ag1);
		ag.setDroit(ag2);
		ad.setGauche(ad1);
		ad.setDroit(ad2);
		ag1.setGauche(f1);
		ag1.setDroit(f2);
		ag2.setGauche(f3);
		ag2.setDroit(f4);
		ad1.setGauche(f5);
		ad1.setDroit(f6);
		ad2.setGauche(f7);
		ad2.setDroit(f8);

		System.out.println(a.parcoursEnProfondeur());
		System.out.println(a.parcoursEnLargeur());

		final LocalDateTime ldt = LocalDateTime.now();
		final DateTimeFormatter dtf = DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println(dtf.format(ldt));
		System.out.println(dtf.format(ldt.minus(5, ChronoUnit.MINUTES)));
		System.out.println(dtf.format(ldt.plus(Period.ofMonths(3))));
		final LocalDateTime ldt2 = ldt.plus(Duration.ofDays(7)).plusMinutes(45);
		System.out.println(dtf.format(ldt2));
		System.out.println(ZoneId.systemDefault());
		System.out.println(Duration.between(ldt, ldt2));

		final Stream<String> s = Stream.of("a", "b", "c", "d", "e");
		System.out.println(s.collect(Collectors.toMap(Function.identity(), (String str) -> (int) str.charAt(0))));
	}

	public static int factIter(final int n) {
		int res = 1;
		for (int i = 2; i <= n; i++) {
			res *= i;
		}
		return res;
	}

	public static int factStream(final int n) {
		return IntStream.rangeClosed(2, n).reduce(1, (o, p) -> o * p);
	}
}