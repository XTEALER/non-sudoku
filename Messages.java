
public class Messages {
    // error cases for types
    int errInt = 0, errStr = 1;

    // help message
    public Object[] Help() {
        Object[] labels = new Object[] { "Instrucciones",
                "La suma de valores en 3 casillas de forma\ndiagonal, horizontal o vertical debe ser\nigual a 15.\n\n¡Escoge tus valores estrategicamente!" };
        return labels;
    }

    // won | lost message
    public Object[] gameState(boolean userHasWon) {
        Object[] labels = new Object[2];
        labels[0] = "PARTIDA FINALIZADA";
        if (userHasWon) {
            labels[1] = "!!!!! HAS GANADO !!!!!";
        } else {
            labels[1] = "!!!!!! HAS PERDIDO ;C !!!!!";
        }
        return labels;
    }

    // error messages for types
    public Object[] errorStates(int errorType) {
        Object[] labels = new Object[2];
        labels[0] = "ERROR";
        if (errorType == errInt) {
            labels[1] = "DIGITE UN NUMERO ENTERO VALIDO";
        } else if (errorType == errStr) {
            labels[1] = "CADENA INVALIDA, VERIFIQUE E INTENTE NUEVAMENTE";
        }
        return labels;
    }

}