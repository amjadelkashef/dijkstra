package edu.iit.network.lsp;

import java.util.List;

public class ValidationUtils {

    public static void checkIfRouterSelectedIsValid(String input, List<List<Integer>> matrix) {
        checkIfInteger(input);
        checkIfRouterExists(input, matrix);
    }

    public static void checkIfInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new LSPException(input + " is not an integer", e);
        }
    }

    public static void checkIfRouterExists(String input, List<List<Integer>> matrix) {
        Integer router = Integer.parseInt(input);
        if (router <= 0 || router > matrix.size()) {
            throw new LSPException("The router " + router + " does not exist.");
        }
    }
}
