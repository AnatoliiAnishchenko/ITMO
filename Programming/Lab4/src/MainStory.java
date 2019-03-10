import java.util.ArrayList;

/*
 * @author Anatolii Anishchenko
 * @variant 15428,518
 */


/*
 * Они разделились, и каждый отправился своим путем на поиски принесенного морем добра и обломков потерпевших крушение
 * кораблей. Интереснее этого занятия нет на свете, ведь можно найти самые удивительные вещи, а вылавливать их из воды
 * зачастую страшно трудно и опасно. Муми-мама спустилась на песчаную площадку, укрытую за огромной скалой. Тут кучками
 * росли голубые морские гвоздики и дикий овес, и в их тонких стеблях шелестел и посвистывал ветер. Муми-мама прилегла
 * у скалы с подветренной стороны. Ей были видны лишь голубое небо да морские гвоздики, качавшиеся над самой ее головой.
 * "Полежу здесь совсем немножко", -- подумала она и тут же заснула глубоким сном на теплом песке.
 *
 * Ну а Снорк -- тот
 * вскарабкался на вершину самой высокой скалы и огляделся. Остров распахнулся перед ним от побережья до побережья и
 * казался букетом цветов, плывущим по неспокойному морю. Вон виднеется маленькая движущаяся точка -- это Снифф, он
 * ищет обломки кораблекрушения, вон мелькнула шляпа Снусмумрика, а вон Хемуль выкапывает особо редкую разновидность
 * венерина башмачка... А вон там -- это как дважды два четыре -- там ударила молния! Большущая каменная глыба, больше,
 * чем десять Муми-домов, вместе взятых, раскололась, словно яблоко, и обе половины раздались в стороны, образовав
 * ущелье с отвесными стенами.
 *
 * С замиранием сердца вошел Снорк в ущелье и осмотрелся. Вот здесь она прошла! Извилистой
 * черной как уголь линией обозначился ее путь по обнажившемуся нутру камня. А с нею рядом бежала другая полоска,
 * светлая и блестящая! Это было золото, не что иное, как золото! Снорк ковырнул полоску ножом. В лапу ему упала
 * золотая крупинка. Он отколупнул еще и еще. Словно в горячке выковыривал он куски один другого крупнее и скоро забыл
 * обо всем на свете, кроме вскрытых молнией золотых жил. Теперь он уже был не какой-то там собиратель выброшенных
 * морем обломков кораблекрушения, а настоящий золотоискатель! Снифф же тем временем сделал совсем немудреное открытие,
 * но и оно подарило ему немудреную, а все же радость: он нашел пробковый пояс -- пояс, отчасти разъеденный морской
 * водой, но пришедшийся ему как раз впору. "Ну вот, теперь я могу выйти на большую воду! -- подумал Снифф. -- Уж
 * теперь-то я наверняка научусь плавать не хуже других! То-то Муми-тролль удивится!"
 */
public class MainStory {
    public static void main(String[] args) {
        Sea sea = new Sea("Sea");
        Area hill = new Area("Hill");
        Area sandPlace = new Area("Sandy Playground");
        Area highestRock = new Area("Highest Rock");
        Area canyon = new Area("Canyon");

        Plant seaPinks = new Plant("Sea Pinks", Plant.Height.HIGH);
        Plant wildOats = new Plant("Wild Oats", Plant.Height.LOW);

        Wind wind = new Wind();
        Thing rock = new Item("rock");

        Item goldLine1 = new GoldLine();
        Item goldLine2 = new GoldLine();
        Item goldLine3 = new GoldLine();

        seaPinks.setArea(sandPlace);
        wildOats.setArea(sandPlace);
        rock.setArea(sandPlace);
        wind.setArea(sandPlace);

        goldLine1.setArea(canyon);
        goldLine2.setArea(canyon);
        goldLine3.setArea(canyon);

        Ship ship = new Ship();
        Thing wreckage1 = sea.destruct(ship);
        Thing wreckage = new Item("good brought by the sea and the wreckage of wrecked ships");
        sea.throwThing(sandPlace, wreckage);

        Person mumiMom = new Person("Mumi Mom", Person.TypeOfPerson.CommonMumi, Person.Gender.FEMALE);
        Person snork = new Person("Snork", Person.TypeOfPerson.Picker, Person.Gender.MALE);
        Person sniff = new Person("Sniff", Person.TypeOfPerson.Picker, Person.Gender.MALE);
        Person snusmumrik = new Person("Snusmumrik", Person.TypeOfPerson.CommonMumi, Person.Gender.MALE);
        Person hemuli = new Person("Hemuli", Person.TypeOfPerson.CommonMumi, Person.Gender.MALE);


        //Part I
        GroupOfPersons they = new GroupOfPersons(mumiMom, snork, sniff);
        ArrayList<Person> mumis = they.divide();

        they.perform(new Action() {
            @Override
            public String describe() {
                return "went their own way in search of " + wreckage;
            }
        });

        they.goLookingFor(null, null);

        mumiMom.setArea(hill);
        mumiMom.goLookingFor(sandPlace, wreckage);

        seaPinks.grow();
        wildOats.grow();
        wind.blow();

        mumiMom.lieNear(rock);
        mumiMom.see();

        seaPinks.wobble();

        mumiMom.think("I'll lie down here quite a bit.");
        mumiMom.sleep();

        //Part II
        snork.moveToArea(highestRock);
        snork.see();

        sniff.goLookingFor(sandPlace, wreckage1);
        snusmumrik.perform(new Action() {
            @Override
            public String describe() {
                return "is playing";
            }
        });
        hemuli.perform(new Action() {
            @Override
            public String describe() {
                Plant ladysSlipper = new Plant("Lady's slipper");
                return "is digging a particularly rare species of " + ladysSlipper;
            }
        });

        //Part III
        snork.moveToArea(canyon);
        snork.setPosition(Person.Position.SIT);
        snork.see();
        snork.setPosition(Person.Position.STAND);

        class Knife extends Item implements Destructing {
            Knife (String name) {
                super (name);
            }

            Knife () {
                super ("knife");
            }

            @Override
            public Item destruct(Item item) {
                System.out.println(this + " destruct " + item);
                String prevItem = item.toString();
                item.breakDown();
                System.out.println(prevItem + " -> " + item);
                return item.produce();
            }
        }

        Knife knife = new Knife();

        snork.pickUp(knife);

        snork.mine(goldLine1);

        snork.setType(Person.TypeOfPerson.GoldMiner);
        snork.mine(goldLine2);
        snork.mine(goldLine3);

        System.out.println(snork + " have:");
        for (Item item : snork.getItems()) {
            System.out.println("\t" + item);
        }

        ArrayList<GoldPiece> goldPieces = snork.getGoldPieces();
        GoldPiece gp0 = goldPieces.get(0);
        GoldPiece gp1 = goldPieces.get(1);
        int compareResult = gp0.compareTo(gp1);
        if (compareResult == 0) {
            System.out.println(gp0 + " have the same size as a " + gp1);
        } else if (compareResult < 0) {
            System.out.println(gp0 + " is smaller than " + gp1);
        } else {
            System.out.println(gp0 + " is bigger than " + gp1);
        }

        while (!ship.isBroken()) {
            sea.destruct(ship);
        }

        Clothes belt = new Clothes("belt");
        sea.destruct(belt);
        sniff.wear(belt);
        sniff.think("Well, now I can get out on the big water!");
        sniff.think("Now I’m surely will learn how to swim better than others! That Mumi will be surprised!");
    }
}