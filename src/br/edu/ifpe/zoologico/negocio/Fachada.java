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

   //CRUD ANIMAL
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

    
    //CRD ZOOLOGICO
    public Zoologico adicionarZoologico(Zoologico zoologico) throws ExcecaoNegocio {
        controladorZoologico.inserir(zoologico); 
        return zoologico; 
    }


    public void removerZoologico(int id) throws ExcecaoNegocio {
        
        Zoologico zoologico = controladorZoologico.consultarPorId(id);
        
        if (zoologico == null) {
            
            throw new ExcecaoNegocio("Zoológico não encontrado.");
        }
        
        
        controladorZoologico.remover(id);
    }


    public Zoologico consultarZoologicoPorId(int id) throws ExcecaoNegocio {
        return controladorZoologico.consultarPorId(id);
    }

    public List<Zoologico> consultarTodosZoologicos() throws ExcecaoNegocio {
        return controladorZoologico.consultarTodos();
    }

}
