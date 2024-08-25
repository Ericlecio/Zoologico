package br.edu.ifpe.zoologico.negocio;

import java.util.List;
import br.edu.ifpe.zoologico.entidades.Animal;
import br.edu.ifpe.zoologico.entidades.Zoologico;
import br.edu.ifpe.zoologico.excecoes.ExcecaoNegocio;

public class Fachada {
    private IControladorAnimal controladorAnimal;
    private IControladorZoologico controladorZoologico;

    public Fachada() {
        controladorAnimal = FabricaControlador.getControladorAnimal();
        controladorZoologico = FabricaControlador.getControladorZoologico();
    }

   
    public void cadastrarAnimal(Animal animal) throws ExcecaoNegocio {
        controladorAnimal.inserir(animal);
    }

    public void editarAnimal(Animal animal) throws ExcecaoNegocio {
        controladorAnimal.editar(animal);
    }

    public void removerAnimal(int id) throws ExcecaoNegocio {
        controladorAnimal.remover(id);
    }

    public Animal consultarAnimalPorId(int id) throws ExcecaoNegocio {
        return controladorAnimal.consultarPorId(id);
    }

    public List<Animal> consultarTodosAnimais() throws ExcecaoNegocio {
        return controladorAnimal.consultarTodos();
    }

    
    public void adicionarZoologico(Zoologico zoologico) throws ExcecaoNegocio {
        controladorZoologico.inserir(zoologico);
    }

    public void removerZoologico(int id) throws ExcecaoNegocio {
        controladorZoologico.remover(id);
    }

    public Zoologico consultarZoologicoPorId(int id) throws ExcecaoNegocio {
        return controladorZoologico.consultarPorId(id);
    }

    public List<Zoologico> listarTodosZoologicos() throws ExcecaoNegocio {
        return controladorZoologico.consultarTodos();
    }

    public void adicionarAnimalAoZoologico(int idZoologico, Animal animal) throws ExcecaoNegocio {
        Zoologico zoologico = controladorZoologico.consultarPorId(idZoologico);
        if (zoologico == null) {
            throw new ExcecaoNegocio("Zoológico não encontrado.");
        }
        zoologico.adicionarAnimal(animal);
      
        controladorZoologico.inserir(zoologico);
    }

    public void removerAnimalDoZoologico(int idZoologico, int idAnimal) throws ExcecaoNegocio {
        Zoologico zoologico = controladorZoologico.consultarPorId(idZoologico);
        if (zoologico == null) {
            throw new ExcecaoNegocio("Zoológico não encontrado.");
        }
        Animal animal = zoologico.encontrarAnimalPorId(idAnimal);
        if (animal == null) {
            throw new ExcecaoNegocio("Animal não encontrado no zoológico.");
        }
        zoologico.removerAnimal(animal);
        
        controladorZoologico.inserir(zoologico);
    }

    public Animal consultarAnimalNoZoologico(int idZoologico, int idAnimal) throws ExcecaoNegocio {
        Zoologico zoologico = controladorZoologico.consultarPorId(idZoologico);
        if (zoologico == null) {
            throw new ExcecaoNegocio("Zoológico não encontrado.");
        }
        return zoologico.encontrarAnimalPorId(idAnimal);
    }
}
