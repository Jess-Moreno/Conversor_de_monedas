import java.util.Map;

public record DatosMoneda(String base_code,
                          Map<String, Double> conversion_rates) {
}