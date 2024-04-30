package ui.cell;

import Setting.Fly;

import static ui.cell.WebCrossWidget.CELL_SIZE;

public class FlyWidget extends InsectWidget{

    private final Fly _fly;

    public FlyWidget(Fly fly){
        super("images/fly.png", (int) (CELL_SIZE/2.6), (int) (CELL_SIZE/1.58));
        _fly = fly;
    }

}
