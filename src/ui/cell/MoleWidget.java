package ui.cell;

import Setting.Mole;

public class MoleWidget extends InsectWidget{

    private Mole _mole;

    public MoleWidget(Mole mole){
        _mole = mole;
        setImage("images/mole.png");
    }
}
