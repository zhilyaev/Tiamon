package ru.diamon.tiamon.util;

public interface FieBasic {
   final String _PET = "PET";   // [String]  имя файла настройки [pet.xml]
   final String _NAME = "NAME"; // [String] Имя
   final String _AGE = "AGE";  // Возраст питомца = время / (1000*60*60*24)  => [Кол-во дней]
   final String _NEXTTIME = "NEXTTIME";   // [long] Время следущего захода в милисекундах
   final String _MONEY = "MONEY"; // [int] Котобаксы
   final String _TIME = "TIME"; // Время усложнения = FIRST_TIME-(U*n) , где n - кол-во заходов
   final String _BURN = "BURN"; // Дата рождения
    final String _LAST = "LAST"; // Время закрытия
    final String _VIRGIN = "VIRGIN"; // Если девственник

}
