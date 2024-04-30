package ui.cell;

import Setting.Fly;

public class FlyWidget extends InsectWidget{

    private final Fly _fly;

    public FlyWidget(Fly fly){
        super("images/fly.png", 46, 76);
        _fly = fly;
    }

}
