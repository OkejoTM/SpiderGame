package Factories;

import Setting.Spider;

public abstract class AbstractSpiderFactory {
    public abstract Spider create(int health);
}
