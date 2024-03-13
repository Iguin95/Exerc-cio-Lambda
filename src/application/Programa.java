package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Digite o caminho completo do arquivo: ");

		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Funcionario> func = new ArrayList<>();

			String linha = br.readLine();

			while (linha != null) {
				String[] campo = linha.split(",");
				func.add(new Funcionario(campo[0], campo[1], Double.parseDouble(campo[2])));
				linha = br.readLine();
			}

			System.out.print("Entre com o salário: ");
			double sal = sc.nextDouble();

			List<String> email = func.stream().filter(f -> f.getSalario() > sal).map(f -> f.getEmail()).sorted()
					.collect(Collectors.toList());

			System.out.println("E-mail das pessoas cujo salário é maior do que " + String.format("%.2f", sal) + ":");
			email.forEach(System.out::println);

			double soma = func.stream().filter(f -> f.getNome().charAt(0) == 'M' || f.getNome().charAt(0) == 'm')
					.map(f -> f.getSalario()).reduce(0.0, (x, y) -> x + y);

			System.out.println("Soma dos salários das pessoas cuja a primeira letra do nome começa com 'M': "
					+ String.format("%.2f", soma));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}
}