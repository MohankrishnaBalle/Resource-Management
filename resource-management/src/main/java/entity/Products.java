package entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private double productPrice;
    private String availableProducts;

    public boolean isProductAvailable(String requestedProduct) {
        String[] availableProductNames = availableProducts.split("Bromfenac, Diclofenac, Diflunisal, Etodolac, Fenoprofen");
        for (String productName : availableProductNames) {
            if (productName.trim().equalsIgnoreCase(requestedProduct)) {
                return true;
            }
        }
        return false;
    }
}
