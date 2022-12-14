import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String inputStr){


        if (splitInputString(inputStr).length != 1) {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] inputArray = splitInputString(inputStr);
                List<Input> inputList = getInputList(inputArray);
                return generateWordFrequencyString(inputList);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }

        return inputStr + " 1";
    }

    private static String generateWordFrequencyString(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        inputList.forEach(input -> {
            joiner.add(MessageFormat.format("{0} {1}",input.getValue(),input.getWordCount()));
        });
        return joiner.toString();
    }

    private List<Input> getInputList(String[] inputWordsArray) {
        List<Input> inputList = generateInitialInputList(inputWordsArray);

        //get the inputListMap for the next step of sizing the same word
        Map<String, List<Input>> inputListMap = getListMap(inputList);

        inputList = getUniqueWords(inputListMap);

        sortInputList(inputList);
        return inputList;
    }

    private static void sortInputList(List<Input> inputList) {
        inputList.sort((word1, word2) -> word2.getWordCount() - word1.getWordCount());
    }

    private static List<Input> getUniqueWords(Map<String, List<Input>> map) {
        List<Input> uniqueWords = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()){
            Input input = new Input(entry.getKey(), entry.getValue().size());
            uniqueWords.add(input);
        }
        return uniqueWords;
    }

    private static List<Input> generateInitialInputList(String[] arr) {
        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }
        return inputList;
    }

    private static String[] splitInputString(String inputStr) {
        return inputStr.split("\\s+");
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList inputArrayList = new ArrayList<>();
                inputArrayList.add(input);
                map.put(input.getValue(), inputArrayList);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }


        return map;
    }


}
