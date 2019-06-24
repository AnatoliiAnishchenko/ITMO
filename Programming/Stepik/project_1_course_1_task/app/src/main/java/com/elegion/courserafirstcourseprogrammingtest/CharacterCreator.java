package com.elegion.courserafirstcourseprogrammingtest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class CharacterCreator extends Observable  implements Serializable{

    public enum Specialization {
        WARRIOR, ARCHER, MAGE
    }

    public enum Race {
        HUMAN, ELF, ORC, DWARF
    }

    public enum Attribute {
        STRENGTH, AGILITY, INTELLECT, STAMINA, LUCK
    }

    public enum Perk {
        BERSERK, CALM, LIGHTWEIGHT, HEAVYARMORED, OBSERVANT, MEDITATIONS
    }

    private String mName;
    private Specialization mSpecialization;
    private Race mRace;
    private int mAvailablePoints;

    private Map<String, Integer> mAttributesMap = new HashMap<>();
    private Map<String, Boolean> mPerksMap = new HashMap<>();


    public CharacterCreator() {
        mRace = Race.HUMAN;
        mSpecialization = Specialization.WARRIOR;
        mAvailablePoints = 5;
        mAttributesMap.put(Attribute.STRENGTH.name(), 5);
        mAttributesMap.put(Attribute.AGILITY.name(), 5);
        mAttributesMap.put(Attribute.INTELLECT.name(), 5);
        mAttributesMap.put(Attribute.STAMINA.name(), 5);
        mAttributesMap.put(Attribute.LUCK.name(), 5);
    }


    public String[] getSpecializations() {
        String[] spec = new String[Specialization.values().length];
        int i = 0;
        for (Specialization s : Specialization.values() ) {
            spec[i] = s.toString().toLowerCase().replaceFirst
                    (s.toString().toLowerCase().charAt(0)+"", s.toString().charAt(0)+"");
            i++;
        }

        return spec;

    }

    public void setSpecialization(int position) {
        if (position < 3 && position > -1) {
            mSpecialization = Specialization.values()[position];
        } else {
            if (position < 0) mSpecialization = Specialization.values()[0];
            else mSpecialization = Specialization.values()[2];
        }
    }

    public String[] getRaces() {
        String[] races = new String[Race.values().length];
        int i = 0;
        for (Race s : Race.values() ) {
            races[i] = s.toString().toLowerCase().replaceFirst
                    (s.toString().toLowerCase().charAt(0)+"", s.toString().charAt(0)+"");
            i++;
        }

        return races;
    }

    public void setRace(int position) {
        if (position < 4 && position > -1) {
            mRace = Race.values()[position];
        } else {
            if (position < 0) mRace = Race.values()[0];
            else mRace = Race.values()[3];
        }
    }

    public String[] getAttributes() {
        String[] attributes = new String[Attribute.values().length];
        int i = 0;
        for (Attribute s : Attribute.values() ) {
            attributes[i] = s.toString().toLowerCase().replaceFirst
                    (s.toString().toLowerCase().charAt(0)+"", s.toString().charAt(0)+"");
            i++;
        }

        return attributes;

    }

    public String[] getPerks() {
        String[] perks = new String[Perk.values().length];
        int i = 0;
        for (Perk s : Perk.values() ) {
            perks[i] = s.toString().toLowerCase().replaceFirst
                    (s.toString().toLowerCase().charAt(0)+"", s.toString().charAt(0)+"");
            i++;
        }

        return perks;

    }
    public void updateAttributeValue(int position, int updateTo) {
        String attribute = getAttributes()[position].toUpperCase();
        int currentValue = mAttributesMap.get(attribute);
        if (updateTo > 0) {
            if (mAvailablePoints >= updateTo) {
                mAttributesMap.put(attribute, currentValue + updateTo);
                mAvailablePoints = mAvailablePoints - updateTo;
            }
        } else {
            if (mAttributesMap.get(attribute) + updateTo > 0) {
                mAttributesMap.put(attribute, currentValue + updateTo);
                mAvailablePoints = mAvailablePoints - updateTo;
            }
        }
        setChanged();
        notifyObservers();
        /*
        *  этот метод увеличивает/уменьшает соответствующее значение атрибута
        *  рекомендуется реализовывать его в последнюю очередь
        *
        * 1. на вход подается позиция изменяемого атрибута и на сколько нужно этот атрибут увеличить/уменьшить
        * 2. по позиции атрибута выявляется название атрибута из enum Attribute
        * 3. по названию атрибута получается значение атрибута из mAttributesMap
        * 4. если атрибут собирается увеличиться и у персонажа достаточно очков для увеличения атрибута (mAvailablePoints)
        *    или
        *    если атрибут собирается уменьшиться и уменьшенный атрибут будет больше 0,
        *    то атрибут изменяется, количество доступных очков меняется в противоположную сторону.
        *
        * 5. убедитесь в том, что измененное значение атрибута записано в mAttributesMap
        * 6. убедитесь в том, что измененное значение количества очков записано в mAvailablePoints;
        * 7. после изменения нужно вызвать методы setChanged(); notifyObservers();
        *    для того, чтобы изменения отразились на верстке
        *
        * пример работы алгоритма.
        * на вход подается (0, -1)
        * из значения 0 выясняем, что меняться будет атрибут STRENGTH
        * получаем текущее значение этого атрибута из mAttributesMap
        * допустим, оно равно 3
        * по условию все алгоритма все проходит
        * сила уменьшается до 2, количество доступных очков увеличивается на +1
        *
        * если STRENGTH равно 1, то ничего не происходит,
        * так как мы не можем уменьшить атрибут ниже 1
        *
        * если на вход пришло (0, 1)
        *   если доступных очков больше 0,
        *       то STRENGTH увеличивается на 1, количество доступных очков уменьшается на 1
        *   если количество доступных очков равно 0
        *       то мы не можем увеличить атрибут, ничего не происходит        *
        * */

    }

    public void setName(String name) {
        mName = name;
    }

    public String getAvailablePoints() {
        return String.valueOf(mAvailablePoints);
    }

    public Map<String, Integer> getAttributesMap() {
        return mAttributesMap;
    }

    public void checkPerk(String text, boolean isChecked) {
        mPerksMap.put(text, isChecked);
    }

    public Character create() {
        Character character = new Character();
        character.setName(mName);
        character.setRace(mRace);
        character.setSpecialization(mSpecialization);
        character.setAttributes(mAttributesMap);
        character.setPerks(mPerksMap);
        character.calculateParameters();
        return character;
    }

    public Specialization getSpecialization() {
        return mSpecialization;
    }

    public Race getRace() {
        return mRace;
    }

    public Map<String, Boolean> getPerksMap() {
        return mPerksMap;
    }

    public void setAvailablePoints(int availablePoints) {
        mAvailablePoints = availablePoints;
    }

    public int getRacePosition() {
        return mRace.ordinal();
    }

    public int getSpecializationPosition() {
        return mSpecialization.ordinal();
    }
}
