package ui.cell;

import Setting.Wasp;

public class WaspWidget extends InsectWidget{

    private final Wasp _wasp;

    public WaspWidget(Wasp wasp){
        _wasp = wasp;
        setImage("images/bee.png");
    }
}
