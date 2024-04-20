package Factories;

import Entities.Spider;

public abstract class AbstractSpiderFactory {
    public abstract Spider create(int health);
}
