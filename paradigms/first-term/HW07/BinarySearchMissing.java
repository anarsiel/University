package search;

public class BinarySearchMissing {

    //Inv0:
    //array - integers po ubivaniyu, key - integer
    //&& right == array length || (array[right] <= key && 0 <= right < array length)
    //&& left == -1 ||  (array[left] >= key && array length > left >=0)
    //&& left' - right' <= (right - left + 1) / 2
    //&& right - left >= 1
    private static int BinarySearchRecursive(int[] array, int left, int right, int key) {
        //Inv0
        if (left >= right - 1) {
            //Inv0 && left >= right - 1
            //array[right] <= key && key - array[right + z] >= key - array[right] for all z > 0
            //&& array[right - z] > key for all z > 0
            //result == right
            if (right != array.length && right != -1 && array[right] == key) {
                //array[right] == key
                return right;
            } else {
                //array[right] most close over key || key > any array element || key < any array element
                return (-right - 1);
            }
        } else {
            //Inv0 && left < right - 1
            int middle = (left + right) / 2;
            //left < middle < right && middle == (left + right) / 2
            if (array[middle] > key)
                //left' == middle
                //array[left'] > key && middle <= result <= right
                return BinarySearchRecursive(array, middle, right, key);
            else
                //right' == middle
                //array[right'] <= key && left <= result <= middle
                return BinarySearchRecursive(array, left, middle, key);
        }
    }
    // Post: (key' == key) && (array[i]' == array[i]) && (array length == 0 || (array[0] < key)) && result == -(-1) - 1) && (array[result] >= key && (result == -(array len - 1)-1 || array[result + 1] < key)

    //Inv0: array - integer array in reverse order, key - integer value
    private static int BinarySearchIterative(int[] array, int key) {
        //Inv0
        int left = -1;
        int right = array.length;
        //Inv1:
        //(right' == array length || (right' < array length && array[right'] <= key)) &&
        //(left' == -1 || ( left' >= 0 && array[left'] >= key)) &&
        //(right'' - left'' <= (right' - left' + 1) / 2)  && (left' <= right' - 1)
        while (left < right - 1) {
            //Inv1 && Inv0 && right' > left' + 1
            int middle = (left + right) / 2;
            //Inv0 && Inv1 && right' <= middle <= left' && right' > left' + 1
            if (array[middle] > key)
                //Inv1 && Inv0 && right' > left' + 1 && array[middle] > key
                left = middle;
                //left'' == middle && right'' = right
                //array[left''] > key &&  left'' <= result <= right''
                //Inv1 && Inv0
            else
                //Inv1 && Inv0 && right' > left' + 1 && array[middle] <=key
                //array[middle] <= key && left <= result <= middle
                right = middle;
            //right'' == middle && left'' == left
            //array[right''] <= key & left'' <= result <= right''
            //Inv1 and Inv0
        }
        //Inv0 && Inv1 && (left' + 1 == right')
        //result == right' &&  ((array[right'] - most close over key || array[right'] == key) || (right == -1 && array[0] < key) || (right == array length && array[len] > key))
        if (right != array.length && array[right] == key) {
            //array[right'] == key && result == right'
            return right;
        } else {
            //array[right'] most close over key || array[0] < key || array[len] > key
            return (-right - 1);
        }
    }
    //post (array[i]' == array[i]) && (result == 0 && array length == 0 ) || (array[len] > key && result == -(array length - 1)-1) || (result < array len && array[result] <= key && (result == 0  || array[result - 1] > key))

    public static void main(String[] args) {
        int[] ints;
        int key;
        try {
            key = Integer.parseInt(args[0]);
            ints = new int[args.length - 1];
            for (int i = 1; i < args.length; i++) {
                ints[i - 1] = Integer.parseInt(args[i]);
            }
            int RecursiveAnsw = BinarySearchRecursive(ints, -1, ints.length, key);
            int BinaryAnsw = BinarySearchIterative(ints, key);

            if (RecursiveAnsw == BinaryAnsw) {
                System.out.println(BinaryAnsw);
            } else {
                System.out.println("Идет мужик с работы. На пути встречается старуха. Старуха протягивает ему свернутую в несколько раз бумагу и настоятельно так произносит: «Сам не решай- дай другим порешать!». Мужик приходит домой, рассказывает жене, что встретил старуху которая дала ему записку, но настоятельно просила: «Сам не решай- дай другим порешать!» Жена взяла записку, развернула ее и сказала: «Да после такого я больше жить с тобой не буду!» И выгнала мужа из дома.\n" +
                        "\n" +
                        "Мужик пошел к лучшему другу проситься на ночлег. Приходит и говорит, что жена выгнала из дома. Тот удивляется: за что? Мужик рассказывает, как встретил старуху, которая дала ему какую-то записку и сказала: «Сам не решай- дай другим порешать!» Жена прочла и выгнала его. Ну тут друг попросил посмотреть записку, чтобы разобраться. Но как только прочел ее, так злобно произнес: «Да после такого я тебе больше никогда не буду другом!» И выгнал мужика.\n" +
                        "\n" +
                        "Идет мужчик по улице. Встречается ему милиционер, интересуется, почему тот в полном одиночестве идет поздно по улице. А мужик рассказывает, что встретил старуху, которая дала ему какую-то записку и сказала: «Сам не решай- дай другим порешать!». Пришел показал жене, а та из дома выгнала. Пришел к лучшему другу, тот тоже прочел записку и тоже выставил его за порог. Милиционер заинтересовался и попросил дать ему эту записку. Прочел и возмутился: «Да за такое тебя судить надо!»\n" +
                        "\n" +
                        "В день суда судья просит мужика объяснить, что произошло. Мужик вспоминает, как встретил бабку, которая дала ему записку и сказала: «Сам не решай- дай другим порешать!!» Дал почитать ее жене, а та из дома выгнала. Друг прочел- отказался от него! Потом встретился милиционер, которому тоже стало интересно, что же написано в этой записке. Прочел, после чего мужик оказался здесь, в суде. Судья так заинтересовался тем, что могло быть в записке, что попросил показать ему ее. Прочтя содержимое он произнес: «Да за такое тебя расстрелять мало!». Мужика расстреляли.\n" +
                        "\n" +
                        "Попал мужик на Тот свет. А там апостол Петр встречает его и спрашивает, что, мол произошло? Ну мужик и давай рассказывать, как встретил старуху, которая дала ему записку и строго наказала: «Сам не решай- дай другим порешать!» Дал жене почитать, а та из дома выгнала. Дал другу почитать, а тот от него отрекся. Милиционер прочел и под суд отдал. А судья после прочтенного приговорил к расстрелу. Апостол Петр заинтересовался и попросил показать ту самую записку. Развернул ее и…: «Да после такого тебе в раю не место!»\n" +
                        "\n" +
                        "Попал мужик в ад. Встречает его чёрт и спрашивает, что произошло и как он к нему попал. Мужик и начал заново свою историю о том, как встретил старуху, которая дала ему записку и просила самому не читать, а дать другим прочитать. Жена из дома выгнала, лучший друг отрекся, милиционер под суд отдал, судья приговорил к казни, из рая тоже выгнали. Ну чёрту стало интересно и он попросил показать ему записку. Прочтя, черт заявил: «Да за такое тебе и в аду не место!».\n" +
                        "\n" +
                        "Попал мужик в Лету. Очутился в лодке с каким-то старцем. Старец спросил, что же такого произошло, что мужик оказался в Лете? Мужик и рассказал, как встретил старуху, которая дала ему записку и сказала: «Сам не решай- дай другим порешать!» Жена из-за прочтенного выгнала из дома, лучший друг тоэже выставил за порог, милиционер отдал под суд, судья приговорил к расстрелу, апостол Петр выгнал из рая, а чёрт из ада. Старец заинтересовался и попросил дать ему записку. Прочитав слова в ней, старец воскликнул: «Да за такое тебе и в моей лодке не место!» …и вышвырнул мужика за борт.\n" +
                        "\n" +
                        "Плывет мужик. Долго плывет. И думает: «Дай-ка я прочту, что же там написано!». Достает он эту записку, разворачивает, а там диффур из третьей контрольной.\n" +
                        "Этим мужиком был Басов");
            }
        } catch (NumberFormatException e) {
            System.err.println("ERROR: input is not numbers");
        } catch (NegativeArraySizeException e) {
            System.err.println("ERROR: input contains not enough arguments");
        }
    }
}