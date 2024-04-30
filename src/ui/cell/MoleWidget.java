package ui.cell;

import Setting.Mole;

public class MoleWidget extends InsectWidget{

    private Mole _mole;

    public MoleWidget(Mole mole){
        super("images/mole.png", 46, 76);
        _mole = mole;
    }
}
