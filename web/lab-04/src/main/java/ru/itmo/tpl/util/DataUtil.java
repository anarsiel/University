package ru.itmo.tpl.util;

import ru.itmo.tpl.model.Color;
import ru.itmo.tpl.model.Post;
import ru.itmo.tpl.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mikhail Mirzayanov", Color.BLUE),
            new User(2, "tourist", "Genady Korotkevich", Color.RED),
            new User(3, "emusk", "Elon Musk", Color.GREEN),
            new User(5, "pashka", "Pavel Mavrin", Color.RED),
            new User(7, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "cannon147", "Erofey Bashunov", Color.GREEN)
    );

    private static final List<Post> POSTS = Arrays.asList(
      new Post(1, "Codeforces Global Round 5",
              "Всем привет!\n" +
                      "\n" +
                      "Скоро состоится Codeforces Global Round 5, время начала: среда, 16 октября 2019 г. в 17:35.\n" +
                      "\n" +
                      "Это пятый раунд из серии Codeforces Global Rounds, которая проводится при поддержке XTX Markets. В раундах могут участвовать все, рейтинг тоже будет пересчитан для всех.\n" +
                      "\n" +
                      "Соревнование продлится 2 часа 30 минут, вас ожидает 8 задач, при этом одна из задач будет представлена в двух версиях.\n" +
                      "\n" +
                      "Разбалловка: 500 — 750 — (750 + 750) — 2000 — 2500 — 3000 — 3750 — 4000\n" +
                      "\n" +
                      "Призы в этом раунде:\n" +
                      "\n" +
                      "30 лучших участников получат футболки.\n" +
                      "20 футболок будут разыграны случайным образом среди участников с 31-го по 500-е место.\n" +
                      "Призы в серии из 6 раундов в 2019 году:\n" +
                      "\n" +
                      "За каждый раунд лучшим 100 участникам начисляются баллы согласно таблице.\n" +
                      "Итоговый результат участника равны сумме баллов для четырех лучших выступлений этого участника.\n" +
                      "Лучшие 20 участников по итоговым результатам получают толстовки и сертификаты с указанием места.\n" +
                      "Задачи раунда разработаны мной, а вот список людей, которым нельзя принимать участие в этом раунде, потому что они знали задачи заранее:\n" +
                      "\n" +
                      "KAN, Endagorion, arsijo, Rox, lperovskaya, Aleks5d, Learner99, MikeMirzayanov.\n" +
                      "\n" +
                      "Так совпало, что это одновременно и те самые люди, которым я благодарен за то, что этот раунд такой, какой он есть.\n" +
                      "\n" +
                      "Раунд будет идеально сбалансирован.",
              2),
            new Post(2, "Codeforces Round #593 (Div. 2)", "Hello, Codeforces!\n" +
                    "\n" +
                    "I'd like to invite you to take part in Codeforces Round #593 (Div. 2). It will held on четверг, 17 октября 2019 г. в 16:35. The round will be rated for the participants with rating lower than 2100.\n" +
                    "\n" +
                    "You will be given 6 problems and 2 hours to solve them.\n" +
                    "\n" +
                    "Scoring distribution: 500—1000—1000—1750—2000—2500.\n" +
                    "\n" +
                    "The problems of this round were developed by me. Thanks a lot to isaf27 for his excellent coordination, to Mike MikeMirzayanov Mirzayanov for Codeforces and Polygon platform, and to marX, antontrygubO_o, NIWIS, win11905 for testing.\n" +
                    "\n" +
                    "This is my first round ever. I hope everyone will enjoy it.\n" +
                    "\n" +
                    "Good luck!",
                    11),
            new Post(4, "Вторая командная интернет-олимпиада, Сезон 2019-20",
                    "Всем привет!\n" +
                            "\n" +
                            "19 октября в 15:00 состоится вторая командная интернет-олимпиада для школьников. Приглашаем вас принять в ней участие! В этот раз вам предстоит помочь или помешать Джокеру.\n" +
                            "\n" +
                            "Продолжительность олимпиады — 3 часа в базовой и 5 часов в усложненной номинациях. Вы сами можете выбрать номинацию, в которой будете участвовать. Не забудьте зарегистрироваться на цикл командных интернет-олимпиад в этом сезоне перед началом олимпиады, если не сделали этого ранее. Обратите внимание, что для участия в командных олимпиадах, нужно зарегистрировать команду (по ссылке \"Новая команда\"). Команда может содержать от 1 до 3 человек. Дополнительную информацию, а также расписание всех предстоящих командных интернет-олимпиад можно посмотреть на страничке интернет-олимпиад.\n" +
                            "\n" +
                            "Условия появятся на сайте в момент начала олимпиады, а также на вкладке \"Файлы\" в тестирующей системе. Тестирующая система находится по адресу pcms.itmo.ru.\n" +
                            "\n" +
                            "Олимпиаду для вас подготовили Николай Будин (Nebuchadnezzar), Ильдар Гайнуллин (300iq), Арсений Кириллов (senek_k), Дмитрий Саютин (_kun_), Михаил Анопренко (manoprenko), Даниил Орешников (doreshnikov), Григорий Шовкопляс (GShark) и Дмитрий Гнатюк (ima_ima_go).\n" +
                            "\n" +
                            "Для связи с жюри можно использовать адрес электронной почты iojury@gmail.com.\n" +
                            "\n" +
                            "Удачи!",
                    5),
            new Post(8, "Codeforces Round #594 (по задачам МКОШП)",
                    "Всем привет!\n" +
                            "\n" +
                            "В воскресенье в Москве пройдет XVII Московская командная олимпиада — командное соревнование для школьников, проходящее в Москве как отборочное соревнование на ВКОШП. Над туром работала Московская методическая комиссия, известная вам также по Открытой олимпиаде школьников по программированию, Московской олимпиаде для 6-9 классов и олимпиаде Мегаполисов (раунды 327, 342, 345, 376, 401, 433, 441, 466, 469, 507, 516, 541, 545, 567, 583).\n" +
                            "\n" +
                            "Раунд состоится в воскресенье, 20 октября 2019 г. в 12:05 и продлится 2 часа. В каждом дивизионе будет предложено по 6 задач.\n" +
                            "\n" +
                            "Задачи соревнования подготовлены wrg0ababd, grikukan, DebNatkh, grphil, voidmax, V--gLaSsH0ldEr593--V, volcolac, Sehnsucht, isaf27, _kun_, Flyrise, Nebuchadnezzar, platypus179, Endagorion под моим руководством, а также GlebsHP, _meshanya_, Endagorion, Zlobober и Андреевой Е. В.\n" +
                            "\n" +
                            "За координацию раунда и перевод условий спасибо _kun_, а так же MikeMirzayanov за системы codeforces и polygon, который использовался при подготовке задач этой олимпиады.\n" +
                            "\n" +
                            "Всем удачи!",
                    7)
    );

    private static List<User> getUsers() {
        return USERS;
    }

    private static List<Post> getPosts() { return POSTS; }

    public static void putData(Map<String, Object> data) {
        data.put("users", getUsers());
        if (data.get("logged_user_id") != null) {
            long logged_user_id = (Long) data.get("logged_user_id");
            for (User user : getUsers()) {
                if (user.getId() == logged_user_id) {
                    data.put("user", user);
                }
            }
        }

        data.put("posts", getPosts());
    }
}
