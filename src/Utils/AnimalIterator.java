//package Utils;
//
//import Entities.Animal;
//
//import java.util.Iterator;
//
//public class AnimalIterator implements Iterator<Animal>{
//
//    private int index = 0;
//
//    @Override
//    public boolean hasNext() {
//        return index < _insectsList.size() + _botSpidersList.size() + (_playerSpider != null ? 1 : 0);
//    }
//
//    @Override
//    public Animal next() {
//        if (index < _insectsList.size()) {
//            return _insectsList.get(index++);
//        } else if (index < _insectsList.size() + _botSpidersList.size()) {
//            return _botSpidersList.get(index++ - _insectsList.size());
//        } else if (_playerSpider != null && index == _insectsList.size() + _botSpidersList.size()) {
//            index++;
//            return _playerSpider;
//        }
//        throw new IndexOutOfBoundsException();
//    }
//}
//
//}
