package br.edu.ifpe.zoologico.negocio;

public class FabricaControlador {
	public static IControladorAnimal getControladorAnimal() {
		return new ControladorAnimal();
	}
	public static IControladorZoologico getControladorZoologico() {
		return new ControladorZoologico();
	}
}
