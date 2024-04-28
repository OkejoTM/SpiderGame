package ui.cell;

import Setting.Fly;

public class FlyWidget extends InsectWidget{

    private final Fly _fly;

    public FlyWidget(Fly fly){
        _fly = fly;
        setImage("images/fly.png");
    }

}
