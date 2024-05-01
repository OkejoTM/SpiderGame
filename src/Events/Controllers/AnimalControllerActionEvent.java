package Events.Controllers;

import Setting.Animal;
import Setting.WebCross;

import java.util.EventObject;

public class AnimalControllerActionEvent extends EventObject {

    private Animal _animal;
    private WebCross _webCross;

    public void setAnimal(Animal animal){
        _animal = animal;
    }

    public Animal getAnimal(){
        return _animal;
    }

    public void setWebCross(WebCross webCross){
        _webCross = webCross;
    }

    public WebCross getWebCross(){
        return _webCross;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public AnimalControllerActionEvent(Object source) {
        super(source);
    }
}
