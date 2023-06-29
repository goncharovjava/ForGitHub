package Tetris;

import java.awt.event.KeyEvent;

public class Tetris {
    private Field field;                //ѕоле с клетками
    private Figure figure;              //‘игурка

    private boolean isGameOver;         //»гра ќкончена?

    public Tetris(int width, int height) {
        field = new Field(width, height);
        figure = null;
    }


    public Field getField() {
        return field;
    }


    public Figure getFigure() {
        return figure;
    }


    public void run() throws Exception {
        //—оздаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //выставл€ем начальное значение переменной "игра окончена" в Ћќ∆№
        isGameOver = false;
        //создаем первую фигурку посередине сверху: x - половина ширины, y - 0.
        figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0);

        //пока игра не окончена
        while (!isGameOver) {
            //"наблюдатель" содержит событи€ о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                //получить самое первое событие из очереди
                KeyEvent event = keyboardObserver.getEventFromTop();
                //≈сли равно символу 'q' - выйти из игры.
                if (event.getKeyChar() == 'q') return;
                //≈сли "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    figure.left();
                    //≈сли "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    figure.right();
                    //≈сли  код клавиши равен 12 ("цифра 5 на доп. клавиатуре") - повернуть фигурку
                else if (event.getKeyCode() == 12)
                    figure.rotate();
                    //≈сли "пробел" - фигурка падает вниз на максимум
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    figure.downMaximum();
            }

            step();             //делаем очередной шаг
            field.print();      //печатаем состо€ние "пол€"
            Thread.sleep(300);  //пауза 300 миллисекунд - 1/3 секунды
        }

        //¬ыводим сообщение "Game Over"
        System.out.println("Game Over");
    }


    public void step() {
        //опускам фигурку вниз

        //если разместить фигурку на текущем месте невозможно:
        //поднимаем обратно
        //приземл€ем
        //удал€ем заполненные линии
        //создаем новую фигурку

    }


    public void setFigure(Figure figure) {
        this.figure = figure;
    }


    public void setField(Field field) {
        this.field = field;
    }

    public static Tetris game;

    public static void main(String[] args) throws Exception {
        game = new Tetris(10, 20);
        game.run();
    }
}
