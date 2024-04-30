package ui.cell;

import Setting.Wasp;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class WaspWidget extends InsectWidget{

    private final Wasp _wasp;

    public WaspWidget(Wasp wasp){
        super("images/bee.png", (int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
        _wasp = wasp;
    }
}
