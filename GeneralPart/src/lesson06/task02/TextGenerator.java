package lesson06.task02;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TextGenerator {

    //ToDO удалить!!!!
    static int allSize;

    final int MAX_COUNT_OF_LETTERS_IN_WORD = 15;
    final int MAX_COUNT_OF_WORDS_IN_SENTENCE = 15;
    final int MAX_COUNT_OF_SPACES_AND_COMMAS_IN_SENTENCE = MAX_COUNT_OF_WORDS_IN_SENTENCE * 2 - 2;
    final int MAX_SIZE_OF_SENTENCES = MAX_COUNT_OF_WORDS_IN_SENTENCE * MAX_COUNT_OF_WORDS_IN_SENTENCE + MAX_COUNT_OF_SPACES_AND_COMMAS_IN_SENTENCE + 1;
    final int MIN_SIZE_OF_SENTENCES = 2;
    final int MAX_COUNT_OF_SENTENCES_IN_PARAGRAPH = 30;

    final int MAX_SIZE_OF_PARAGRAPH = MAX_COUNT_OF_SENTENCES_IN_PARAGRAPH * (MAX_SIZE_OF_SENTENCES) + 3;
    final int MIN_SIZE_OF_PARAGRAPH = 5;

    public String sentenceGenerator(String arrWord[], int probability) {

        double doubleProbability = (double) probability / 100;
        char[] endsOfSentences = new char[]{'.', '!', '?'};
        int senSize = (int) (1 + (Math.random() * MAX_COUNT_OF_WORDS_IN_SENTENCE));

        ArrayList<String> listOfWords = new ArrayList<>(senSize);

        StringBuilder sentence = new StringBuilder();

        if (Math.random() < doubleProbability) {

            listOfWords.add(RandomStringUtils.randomAlphabetic(1, 15).toLowerCase());
            sentence.append(listOfWords.get(0).substring(0, 1).toUpperCase()
                    + listOfWords.get(0).substring(1));

            for (int i = 0; i < senSize - 1; i++) {
                listOfWords.add(RandomStringUtils.randomAlphabetic(1, 15).toLowerCase());
            }

            sentence.append(endsOfSentences[(int) ((Math.random() * 3))]);

            return new String(sentence);
        }


        for (int i = 0; i < senSize - 1; i++) {
            listOfWords.add(RandomStringUtils.randomAlphabetic(1, 15).toLowerCase());
        }

        String dictionaryWord = (arrWord[(int) ((Math.random() * 1000))]);

        listOfWords.add((int) ((Math.random() * senSize)), dictionaryWord);

        sentence.append(listOfWords.get(0).substring(0, 1).toUpperCase()
                + listOfWords.get(0).substring(1));

        for (int i = 1; i < senSize - 1; i++) {
            sentence.append(" " + listOfWords.get(i));
            if (Math.random() < 0.4) {
                sentence.append(',');
            }
        }
        if (senSize > 1) {
            sentence.append(" " + listOfWords.get(senSize - 1));
        }
        sentence.append(endsOfSentences[(int) ((Math.random() * 3))]);

        return new String(sentence);

    }

    public String paragraphGenerator(String[] arrWord, int probability) {
        int paragraphSize = (int) (1 + Math.random() * 30);

        StringBuilder paragraph = new StringBuilder();

        paragraph.append("\t");

        for (int i = 0; i < paragraphSize - 1; i++) {
            paragraph.append(sentenceGenerator(arrWord, probability) + " ");
        }
        paragraph.append(sentenceGenerator(arrWord, probability));
        paragraph.append("\n\r");

        System.out.println("size of paragraph: " + (new String(paragraph)).length());
        System.out.println("size of paragraph(bytes): " + (new String(paragraph)).getBytes().length);

        return new String(paragraph);

    }

    public String lastSentenceGenerator(String arrWord[], int probability, int availableSize) {

        System.out.println("availableSize for sentence " + availableSize + " (" + MAX_SIZE_OF_SENTENCES + ")");
        char[] endsOfSentences = new char[]{'.', '!', '?'};
        availableSize--;

        System.out.println(availableSize);

        StringBuilder sentence = new StringBuilder();

        double doubleProbability = (double) probability / 100;


        if (Math.random() <= doubleProbability) {
            System.out.println("Yes");

            String dictionaryWord = null;

            ArrayList<String> dictionaryWords = new ArrayList<>();


            if (availableSize <= 15) {


                for (int i = 0; i < arrWord.length; i++) {

                    if (arrWord[i].length() == availableSize) {

                        dictionaryWords.add(arrWord[i]);
                    }

                }


            }

            if (availableSize == 16) {

                for (int i = 0; i < arrWord.length; i++) {

                    if (arrWord[i].length() == 14) {

                        dictionaryWords.add(arrWord[i]);
                    }

                }

                if (dictionaryWords.size() > 0) {
                    availableSize = 1;
                }
            }

            if (availableSize > 16) {

                dictionaryWord = (arrWord[(int) ((Math.random() * 1000))]);

                System.out.println(availableSize);

                availableSize -= (dictionaryWord.length() + 1);

                System.out.println(dictionaryWord);

                System.out.println("После " + availableSize);

                System.out.println("Chet 1 " + dictionaryWord.length());
            }

            if (dictionaryWords.size() > 0 || dictionaryWord != null) {
                int chet = 0;
                if (dictionaryWords.size() != 0) {
                    Collections.shuffle(dictionaryWords);
                    dictionaryWord = dictionaryWords.get(0);
                }

                ArrayList<String> wordList = new ArrayList<>();
                wordList.add(dictionaryWord);

                chet += dictionaryWord.length();
                while (availableSize > MAX_COUNT_OF_WORDS_IN_SENTENCE + 1) {
                    String unitOfWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_WORDS_IN_SENTENCE).toLowerCase();
                    wordList.add(unitOfWord);
                    availableSize -= (MAX_COUNT_OF_WORDS_IN_SENTENCE + 1);
                    chet += unitOfWord.length();
                    System.out.println("next ava " + availableSize);
                    System.out.println("next chet " + chet);
                }
                System.out.println("chet " + chet);

                String lastWord;

                System.out.println("size for Lastword " + availableSize);
                if (availableSize <= MAX_COUNT_OF_WORDS_IN_SENTENCE) {
                    lastWord = RandomStringUtils.randomAlphabetic(availableSize).toLowerCase();
                    availableSize -= lastWord.length();
                    System.out.println(lastWord);
                } else {
                    lastWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_WORDS_IN_SENTENCE).toLowerCase();
                    availableSize -= MAX_COUNT_OF_WORDS_IN_SENTENCE;
                }

                wordList.add(lastWord);

                Collections.shuffle(wordList);

                //время запятых

                System.out.println("зп1 " + availableSize);

                if (availableSize > 0) {
                    wordList.set(0, wordList.get(0) + ",");
                    availableSize--;
                }
                System.out.println("зп2 " + availableSize);

                int i = 0;
                while (availableSize > 0) {
                    wordList.set(i, wordList.get(i) + ",");
                    System.out.println(false);
                    availableSize--;
                    i++;
                }


                String firstWord = wordList.get(0);

                if (wordList.size() > 0) {
                    sentence.append(firstWord.substring(0, 1).toUpperCase()
                            + firstWord.substring(1) + " ");
                } else {
                    sentence.append(firstWord.substring(0, 1).toUpperCase()
                            + firstWord.substring(1) + " ");
                }


                for (int j = 1; j < wordList.size() - 1; j++) {
                    sentence.append(wordList.get(j) + " ");
                }

                sentence.append(wordList.get(wordList.size() - 1));

                sentence.append(endsOfSentences[(int) ((Math.random() * 3))]);

                System.out.println("last sentence with dicword" + (new String(sentence)).length());

                return new String(sentence);

            }
////
////            sentence.append(dictionaryWord.substring(0, 1).toUpperCase()
////                    + dictionaryWord.substring(1));


        }

        String firstWord;


        if (availableSize == MAX_COUNT_OF_LETTERS_IN_WORD + 1) {

            firstWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_LETTERS_IN_WORD - 2).toLowerCase();
            sentence.append(firstWord.substring(0, 1).toUpperCase()
                    + firstWord.substring(1));
            availableSize -= MAX_COUNT_OF_LETTERS_IN_WORD - 2;

        } else if (availableSize < MAX_COUNT_OF_LETTERS_IN_WORD) {

            System.out.println("Fuck! " + availableSize);
            firstWord = RandomStringUtils.randomAlphabetic(availableSize).toLowerCase();

            System.out.println(availableSize + " " + firstWord);
            sentence.append(firstWord.substring(0, 1).toUpperCase()
                    + firstWord.substring(1));
            availableSize = 0;

        } else if (availableSize == 15) {

            firstWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_LETTERS_IN_WORD).toLowerCase();
            sentence.append(firstWord.substring(0, 1).toUpperCase()
                    + firstWord.substring(1));
            availableSize -= (MAX_COUNT_OF_LETTERS_IN_WORD);

        } else {

            firstWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_LETTERS_IN_WORD).toLowerCase();
            sentence.append(firstWord.substring(0, 1).toUpperCase()
                    + firstWord.substring(1));
            availableSize -= (MAX_COUNT_OF_LETTERS_IN_WORD + 1);

        }

        //Пробелы учитываем, но не добавляем

        ArrayList<String> wordList = new ArrayList<>();

        int chet = 0;
        while (availableSize > MAX_COUNT_OF_WORDS_IN_SENTENCE + 1) {
            String unitOfWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_WORDS_IN_SENTENCE).toLowerCase();
            wordList.add(unitOfWord);
            availableSize -= (MAX_COUNT_OF_WORDS_IN_SENTENCE + 1);
            chet++;
        }

        String lastWord = "";

        if (availableSize <= MAX_COUNT_OF_WORDS_IN_SENTENCE) {
            lastWord = RandomStringUtils.randomAlphabetic(availableSize).toLowerCase();
            availableSize -=availableSize;
        } else {
            lastWord = RandomStringUtils.randomAlphabetic(MAX_COUNT_OF_WORDS_IN_SENTENCE).toLowerCase();
            availableSize -= MAX_COUNT_OF_WORDS_IN_SENTENCE;
        }

        //время запятых

        if (availableSize > 0) {
            sentence.append(',');
            availableSize--;
        }

        if (wordList.size() > 0) {
            sentence.append(' ');
        }

        int i = 0;
        while (availableSize > 0) {
            try {
                wordList.set(i, wordList.get(i) + ",");
            } catch (IndexOutOfBoundsException e){
                System.out.println("!!!!!!!!!!!!!!!!");
                int look = 0;
                for (String s :
                        wordList) {
                    System.out.println(s + " " + s.length());
                    look += s.length();
                }
                System.out.println(look);
                System.out.println("chet " + chet + " i " +i + " ava " + availableSize + " lastword " + lastWord);
                System.out.println("!!!!!!!!!!!!!!!!");
            }

            availableSize--;
            i++;
        }

        Collections.shuffle(wordList);

        for (String word :
                wordList) {
            sentence.append(word);
            sentence.append(' ');
        }

        sentence.append(lastWord);

        sentence.append(endsOfSentences[(int) ((Math.random() * 3))]);

        System.out.println("last sentence " + (new String(sentence)).length());

        return new String(sentence);
    }


    public String lastParagraphGenerator(String[] arrWord, int probability, int availableSize) {

        StringBuilder paragraph = new StringBuilder();

        System.out.println("ava for lastpar " + availableSize);

        availableSize -= 1;

        paragraph.append("\t");

        while (availableSize >= MAX_SIZE_OF_SENTENCES) {
            String sentence = sentenceGenerator(arrWord, probability);
            paragraph.append(sentence + " ");
            availableSize = availableSize - sentence.length() - 1;
//            System.out.println(availableSize);
        }
        System.out.println(MAX_SIZE_OF_SENTENCES);
        System.out.println("? " + availableSize);
        paragraph.append(lastSentenceGenerator(arrWord, probability, availableSize));
//        paragraph.append("\n\r");

        System.out.println("! " + (new String(paragraph)).length());


        return new String(paragraph);

    }

    private ByteArrayOutputStream fileGenerator(int availableSize, String[] words, int probability) throws IOException {

        ByteArrayOutputStream total = null;
        System.out.println("Счет " + allSize);

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            System.out.println(availableSize + " " + MAX_SIZE_OF_PARAGRAPH);
            while (availableSize >= MAX_SIZE_OF_PARAGRAPH + MIN_SIZE_OF_PARAGRAPH) {
                System.out.println(availableSize);
                String paragraph = paragraphGenerator(words, probability);
                System.out.println("Параграф на выходе: " + paragraph.length());
                byteArrayOutputStream.write(paragraph.getBytes());
                availableSize -= paragraph.length();
                allSize += byteArrayOutputStream.size();
                System.out.println("Счет " + allSize);
                System.out.println("Остаток " + availableSize);
            }


            byteArrayOutputStream.write(lastParagraphGenerator(words, probability, availableSize).getBytes());


            total = byteArrayOutputStream;

            System.out.println("Итоговый размер " + byteArrayOutputStream.size());
        }
        return total;
    }

    public void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {

        for (int i = 0; i < n; i++) {

            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path + "" + i + ".txt"))) {
                fileGenerator(size, words, probability).writeTo(bufferedOutputStream);
            }

        }
    }

}
