package br.dev.victor696.simpleeconomy.manager;

import java.util.ArrayList;
import java.util.List;

import br.dev.victor696.simpleeconomy.SimpleEconomy;

public class MessagesManager {
	
	public static String AddArgumento;
	public static String ValorAdicionado;
	public static String JogadorNaoEncontrado;
	public static String ValorPrecisaSerNumero;
	public static String ValorPrecisaSerMaiorQueZero;
	public static String RemoveArgumento;
	public static String ValorRemovido;
	public static String ValorRemovidoMaisDoQueTinha;
	public static String MoneyDoJogador;
	public static String MoneyDeOutroJogador;
	public static String SemPermissao;
	public static String SetArgumento;
	public static String ValorSetado;
	public static String ValorPrecisaSerMaiorOuIgualAZero;
	public static String PayArgumento;
	public static String JogadorMoneySuficiente;
	public static String JogadorEnviouMoney;
	public static String JogadorRecebeuMoney;
	public static String ToggleArgumento;
	public static String JogadorAtivouToggle;
	public static String JogadorDesativouToggle;
	public static String JogadorNaoEstaRecebendoMoney;
	public static String MagnataAtual;
	public static String TopFormato;
	public static String TagMagnata;
	public static String NaoHaMagnata;
	public static String SiMesmo;
	public static List<String> TopPrefixo = new ArrayList<>();
	public static int TopTamanho;
	
	public static void loadMessages() {
		AddArgumento = SimpleEconomy.getInstance().getConfig().getString("Mensagens.AddArgumento").replaceAll("&", "§");
		NaoHaMagnata = SimpleEconomy.getInstance().getConfig().getString("Mensagens.NaoHaMagnata").replaceAll("&", "§");
		SiMesmo = SimpleEconomy.getInstance().getConfig().getString("Mensagens.SiMesmo").replaceAll("&", "§");
		MagnataAtual = SimpleEconomy.getInstance().getConfig().getString("Mensagens.MagnataAtual").replaceAll("&", "§");
		ValorAdicionado = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorAdicionado").replaceAll("&", "§");
		JogadorNaoEncontrado = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorNaoEncontrado").replaceAll("&", "§");
		ValorPrecisaSerNumero = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerNumero").replaceAll("&", "§");
		ValorPrecisaSerMaiorQueZero = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerMaiorQueZero").replaceAll("&", "§");
		RemoveArgumento = SimpleEconomy.getInstance().getConfig().getString("Mensagens.RemoveArgumento").replaceAll("&", "§");
		ValorRemovido = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorRemovido").replaceAll("&", "§");
		ValorRemovidoMaisDoQueTinha = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorRemovidoMaisDoQueTinha").replaceAll("&", "§");
		MoneyDoJogador = SimpleEconomy.getInstance().getConfig().getString("Mensagens.MoneyDoJogador").replaceAll("&", "§");
		MoneyDeOutroJogador = SimpleEconomy.getInstance().getConfig().getString("Mensagens.MoneyDeOutroJogador").replaceAll("&", "§");
		SemPermissao = SimpleEconomy.getInstance().getConfig().getString("Mensagens.SemPermissao").replaceAll("&", "§");
		SetArgumento = SimpleEconomy.getInstance().getConfig().getString("Mensagens.SetArgumento").replaceAll("&", "§");
		ValorSetado = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorSetado").replaceAll("&", "§");
		ValorPrecisaSerMaiorOuIgualAZero = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ValorPrecisaSerMaiorOuIgualAZero").replaceAll("&", "§");
		PayArgumento = SimpleEconomy.getInstance().getConfig().getString("Mensagens.PayArgumento").replaceAll("&", "§");
		JogadorMoneySuficiente = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorMoneySuficiente").replaceAll("&", "§");
		JogadorEnviouMoney = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorEnviouMoney").replaceAll("&", "§");
		JogadorRecebeuMoney = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorRecebeuMoney").replaceAll("&", "§");
		ToggleArgumento = SimpleEconomy.getInstance().getConfig().getString("Mensagens.ToggleArgumento").replaceAll("&", "§");
		JogadorAtivouToggle = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorAtivouToggle").replaceAll("&", "§");
		JogadorDesativouToggle = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorDesativouToggle").replaceAll("&", "§");
		JogadorNaoEstaRecebendoMoney = SimpleEconomy.getInstance().getConfig().getString("Mensagens.JogadorNaoEstaRecebendoMoney").replaceAll("&", "§");
		TopFormato = SimpleEconomy.getInstance().getConfig().getString("Mensagens.TopFormato").replaceAll("&", "§");
		TopPrefixo = SimpleEconomy.getInstance().getConfig().getStringList("Mensagens.TopPrefixo");
		TopTamanho = SimpleEconomy.getInstance().getConfig().getInt("Geral.TopTamanho");
		TagMagnata = SimpleEconomy.getInstance().getConfig().getString("Geral.TagMagnata").replaceAll("&", "§");
	}

}
