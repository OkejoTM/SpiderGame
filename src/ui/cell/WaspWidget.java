package ui.cell;

import Setting.Wasp;

public class WaspWidget extends InsectWidget{

    private final Wasp _wasp;

    public WaspWidget(Wasp wasp){
        super("images/bee.png", 46, 76);
        _wasp = wasp;
    }
}
