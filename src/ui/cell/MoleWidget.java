package ui.cell;

import Setting.Mole;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class MoleWidget extends InsectWidget{

    private Mole _mole;

    public MoleWidget(Mole mole){
        super("images/mole.png", (int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
        _mole = mole;
    }
}
