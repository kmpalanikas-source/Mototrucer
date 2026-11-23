package gr.globalcartracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Car(
    val make: String, val model: String, val years: String, val market: String,
    val fuel: String, val engine: String, val power: String, val torque: String,
    val transmission: String, val drive: String, val consumption: String,
    val dimensions: String, val wheelbase: String, val weight: String,
    val battery: String, val range: String, val acceleration: String,
    val topSpeed: String, val notes: String, val source: String
)

private val cars = listOf(
    Car("Toyota","Corolla (E210)","2020–2026","Ιαπωνία / Ευρώπη / Αμερική / Ασία","Βενζίνη / Hybrid","1.8 ή 2.0 λίτρα, ανά αγορά","122–196 hp","142–190 Nm","e-CVT / CVT / χειροκίνητο","FWD","4,4–7,0 l/100 km","4.630 × 1.780 × 1.435 mm (sedan, ενδεικτικά)","2.700 mm","1.300–1.500 kg","—","—","ανά έκδοση","ανά έκδοση","Οι κινητήρες και οι επιδόσεις διαφέρουν σημαντικά ανά χώρα και αμάξωμα.","https://www.toyota.com/"),
    Car("Toyota","Camry (XV80)","2024–2026","Αμερική / Ασία / Ευρώπη επιλεγμένες αγορές","Hybrid","2.5 λίτρα, 4κύλινδρο","225–232 hp","221 Nm (θερμικός, ενδεικτικά)","e-CVT","FWD / AWD ανά αγορά","περίπου 4,5–5,5 l/100 km","4.915 × 1.840 × 1.445 mm (ενδεικτικά)","2.825 mm","1.600–1.750 kg","—","—","περίπου 7–8 s","ανά έκδοση","Η νεότερη γενιά διατίθεται κυρίως ως υβριδική· η ισχύς αλλάζει με το σύστημα κίνησης.","https://www.toyota.com/camry/"),
    Car("Toyota","RAV4 (XA50)","2020–2026","Παγκόσμια","Βενζίνη / Hybrid / Plug-in Hybrid","2.0, 2.5 λίτρα ανά αγορά","170–306 hp","ανά έκδοση","CVT / e-CVT / αυτόματο","FWD / AWD","4,5–8,0 l/100 km","4.600 × 1.855 × 1.685 mm (ενδεικτικά)","2.690 mm","1.550–1.950 kg","έως περίπου 18 kWh PHEV","ηλεκτρική αυτονομία ανά αγορά","5,8–10 s","ανά έκδοση","Πολύ διαφορετικές εκδόσεις για Ευρώπη, ΗΠΑ, Ιαπωνία και Κίνα.","https://www.toyota.com/rav4/"),
    Car("Toyota","Prius (5th Gen)","2023–2026","Ιαπωνία / Αμερική / Ευρώπη","Hybrid / Plug-in Hybrid","2.0 λίτρα","194 hp HEV / έως 223 hp PHEV (ΗΠΑ)","ανά έκδοση","e-CVT","FWD / AWD ανά αγορά","περίπου 4,1–5,0 l/100 km","4.600 × 1.780 × 1.430 mm (ενδεικτικά)","2.750 mm","1.400–1.600 kg","PHEV περίπου 13,6 kWh","ηλεκτρική αυτονομία ανά αγορά","περίπου 6,7 s PHEV","ανά έκδοση","Οι προδιαγραφές διαφέρουν μεταξύ ευρωπαϊκής, ιαπωνικής και αμερικανικής έκδοσης.","https://www.toyota.com/prius/"),
    Car("Toyota","Land Cruiser 250","2024–2026","Ευρώπη / Ασία / Αμερική επιλεγμένες αγορές","Diesel / Βενζίνη / Hybrid","2.4 turbo / 2.8 diesel / 2.7 βενζίνη ανά αγορά","163–330 hp","ανά έκδοση","8AT / 6AT","4WD","ανά έκδοση","4.925 × 1.980 × 1.935 mm (ενδεικτικά)","2.850 mm","2.300–2.600 kg","—","—","ανά έκδοση","ανά έκδοση","Πολλαπλά σύνολα κίνησης ανά χώρα. Η αμερικανική έκδοση διαφέρει από την ευρωπαϊκή.","https://www.toyota.com/"),
    Car("Honda","Civic (11th Gen)","2022–2026","Αμερική / Ευρώπη / Ασία","Βενζίνη / Hybrid","1.5 turbo ή 2.0 hybrid","150–200 hp","ανά έκδοση","CVT / e-CVT / χειροκίνητο","FWD","4,0–7,5 l/100 km","4.550 × 1.800 × 1.415 mm (sedan, ενδεικτικά)","2.735 mm","1.300–1.500 kg","—","—","ανά έκδοση","ανά έκδοση","Υπάρχουν sedan, hatchback και διαφορετικές εκδόσεις ανά ήπειρο.","https://automobiles.honda.com/civic"),
    Car("Honda","CR-V (6th Gen)","2023–2026","Αμερική / Ευρώπη / Ασία","Βενζίνη / Hybrid / Plug-in Hybrid","1.5 turbo ή 2.0 hybrid","184–204 hp","ανά έκδοση","CVT / e-CVT","FWD / AWD","5,0–8,5 l/100 km","4.706 × 1.866 × 1.681 mm (ενδεικτικά)","2.700 mm","1.600–1.900 kg","PHEV ανά αγορά","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Η ευρωπαϊκή e:PHEV και οι βορειοαμερικανικές εκδόσεις έχουν διαφορετικές προδιαγραφές.","https://automobiles.honda.com/cr-v"),
    Car("Honda","Accord (11th Gen)","2023–2026","Αμερική / Κίνα / Ασία","Hybrid / Βενζίνη","1.5 turbo ή 2.0 hybrid","192–204 hp","ανά έκδοση","CVT / e-CVT","FWD","ανά αγορά","4.971 × 1.862 × 1.450 mm (ενδεικτικά)","2.830 mm","1.500–1.650 kg","—","—","ανά έκδοση","ανά έκδοση","Η κινεζική και η αμερικανική έκδοση δεν έχουν τα ίδια σύνολα κίνησης.","https://automobiles.honda.com/accord"),
    Car("Nissan","X-Trail / Rogue","2021–2026","Ευρώπη / Αμερική / Ιαπωνία","Βενζίνη / e-POWER Hybrid","1.5 VC-Turbo ή e-POWER 1.5","163–213 hp","ανά έκδοση","CVT / e-POWER","FWD / AWD","5,5–8,0 l/100 km","4.680 × 1.840 × 1.725 mm (ενδεικτικά)","2.705 mm","1.600–1.850 kg","—","—","ανά έκδοση","ανά έκδοση","Το Rogue των ΗΠΑ και το X-Trail άλλων αγορών έχουν διαφορές σε κινητήρες και εξοπλισμό.","https://www.nissan-global.com/"),
    Car("Hyundai","Tucson (NX4)","2021–2026","Ευρώπη / Αμερική / Κορέα / Ασία","Βενζίνη / Diesel / Hybrid / PHEV","1.6 T-GDi, 2.0, 2.5 ανά αγορά","115–265 hp","ανά έκδοση","6MT / 7DCT / 8AT / 6AT","FWD / AWD","ανά έκδοση","4.500 × 1.865 × 1.650 mm (ενδεικτικά)","2.680 mm","1.450–1.850 kg","PHEV περίπου 13,8 kWh","ηλεκτρική αυτονομία ανά αγορά","ανά έκδοση","ανά έκδοση","Μεγάλη διαφοροποίηση κινητήρων ανά ήπειρο και έτος ανανέωσης.","https://www.hyundai.com/"),
    Car("Kia","Sportage (NQ5)","2022–2026","Ευρώπη / Αμερική / Κορέα / Ασία","Βενζίνη / Diesel / Hybrid / PHEV","1.6 T-GDi, 2.0, 2.5 ανά αγορά","115–265 hp","ανά έκδοση","6MT / 7DCT / 8AT / 6AT","FWD / AWD","ανά έκδοση","4.515 × 1.865 × 1.645 mm (ενδεικτικά)","2.680 mm","1.450–1.900 kg","PHEV περίπου 13,8 kWh","ανά έκδοση","ανά έκδοση","ανά έκδοση","Οι ευρωπαϊκές και αμερικανικές εκδόσεις έχουν διαφορετικό μεταξόνιο και κινητήρες.","https://www.kia.com/"),
    Car("Genesis","GV80","2020–2026","Κορέα / Αμερική / Μέση Ανατολή","Βενζίνη / Diesel (επιλεγμένες αγορές)","2.5T, 3.5T, 3.0 diesel","278–375 hp","ανά έκδοση","8AT","RWD / AWD","ανά αγορά","4.945 × 1.975 × 1.715 mm (ενδεικτικά)","2.955 mm","2.000–2.300 kg","—","—","ανά έκδοση","ανά έκδοση","Πολυτελές SUV· διαθεσιμότητα diesel και εξοπλισμός αλλάζουν ανά αγορά.","https://www.genesis.com/"),
    Car("Volkswagen","Golf VIII","2020–2026","Ευρώπη / Ασία / Αμερική επιλεγμένες αγορές","Βενζίνη / Diesel / Mild Hybrid / PHEV","1.0, 1.5, 2.0 TSI / 2.0 TDI","110–333 hp","ανά έκδοση","6MT / 7DSG","FWD / AWD (R)","ανά έκδοση","4.284 × 1.789 × 1.456 mm (ενδεικτικά)","2.619 mm","1.250–1.600 kg","PHEV περίπου 19,7 kWh (νεότερες εκδόσεις)","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Περιλαμβάνει eTSI, eHybrid, GTI και R· τα στοιχεία εξαρτώνται από έκδοση και facelift.","https://www.volkswagen.com/"),
    Car("Volkswagen","Tiguan (3rd Gen)","2024–2026","Ευρώπη / Αμερική / Ασία","Βενζίνη / Diesel / PHEV","1.5 eTSI, 2.0 TSI/TDI","130–272 hp","ανά έκδοση","7DSG","FWD / AWD","ανά έκδοση","4.539 × 1.842 × 1.639 mm (ενδεικτικά)","2.680 mm","1.600–1.900 kg","PHEV περίπου 19,7 kWh","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Οι εκδόσεις ΗΠΑ διαφέρουν σε αμάξωμα, διαστάσεις και ονομασία.","https://www.volkswagen.com/"),
    Car("BMW","3 Series (G20 LCI)","2020–2026","Ευρώπη / Αμερική / Ασία","Βενζίνη / Diesel / PHEV","2.0 / 3.0 λίτρα","150–510 hp","ανά έκδοση","8-speed Steptronic","RWD / xDrive","ανά έκδοση","4.713 × 1.827 × 1.440 mm (sedan, ενδεικτικά)","2.851 mm","1.500–1.900 kg","PHEV ανά έκδοση","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Περιλαμβάνει 318i έως M3· οι επιδόσεις δεν είναι ενιαίες για όλη τη σειρά.","https://www.bmw.com/"),
    Car("BMW","5 Series (G60)","2023–2026","Ευρώπη / Αμερική / Ασία","Βενζίνη / Diesel / PHEV / EV","2.0 / 3.0 λίτρα ή ηλεκτρικό","197–601 hp","ανά έκδοση","8AT / 1-speed EV","RWD / xDrive","ανά έκδοση","5.060 × 1.900 × 1.515 mm (ενδεικτικά)","2.995 mm","1.900–2.400 kg","PHEV ή EV ανά μοντέλο","έως περίπου 580 km WLTP (i5, ανά έκδοση)","ανά έκδοση","ανά έκδοση","Η οικογένεια περιλαμβάνει 520i, 530e, 540i, i5 και M5 με διαφορετική τεχνολογία.","https://www.bmw.com/"),
    Car("Mercedes-Benz","C-Class (W206)","2021–2026","Ευρώπη / Αμερική / Ασία","Βενζίνη / Diesel / PHEV","1.5 / 2.0 λίτρα","170–680+ hp","ανά έκδοση","9G-TRONIC","RWD / 4MATIC","ανά έκδοση","4.751 × 1.820 × 1.438 mm (sedan, ενδεικτικά)","2.865 mm","1.650–2.200 kg","PHEV ανά έκδοση","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Περιλαμβάνει AMG και plug-in εκδόσεις· τεχνικά στοιχεία ανά κωδικό κινητήρα.","https://www.mercedes-benz.com/"),
    Car("Mercedes-Benz","E-Class (W214)","2023–2026","Ευρώπη / Αμερική / Ασία","Βενζίνη / Diesel / PHEV","2.0 / 3.0 λίτρα","197–381 hp","ανά έκδοση","9G-TRONIC","RWD / 4MATIC","ανά έκδοση","4.949 × 1.880 × 1.468 mm (ενδεικτικά)","2.961 mm","1.850–2.300 kg","PHEV ανά έκδοση","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Νέα γενιά με ήπια υβριδικά και plug-in σύνολα ανά αγορά.","https://www.mercedes-benz.com/"),
    Car("Porsche","Taycan","2020–2026","Ευρώπη / Αμερική / Ασία","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρες, 800V αρχιτεκτονική","408–1.034 hp","ανά έκδοση","2-speed πίσω άξονας (ορισμένες εκδόσεις)","RWD / AWD","—","4.963 × 1.966 × 1.379 mm (ενδεικτικά)","2.900 mm","2.100–2.400 kg","περίπου 89–105 kWh gross ανά έκδοση","WLTP έως περίπου 678 km (ανά έκδοση/έτος)","ανά έκδοση","ανά έκδοση","Μεγάλη διαφορά μεταξύ βασικού Taycan, 4S, Turbo και Turbo GT.","https://www.porsche.com/"),
    Car("Tesla","Model 3","2020–2026","Αμερική / Ευρώπη / Κίνα / Ασία","Αμιγώς ηλεκτρικό","Μόνιμου μαγνήτη / επαγωγικοί κινητήρες ανά έκδοση","283–460+ hp (εκτιμήσεις ανά έκδοση)","ανά έκδοση","1-speed","RWD / AWD","—","4.720 × 1.850 × 1.440 mm (ενδεικτικά)","2.875 mm","1.750–1.850 kg","περίπου 60–79 kWh usable ανά έκδοση","περίπου 513–629 km WLTP ανά έκδοση","3,1–6,1 s","ανά έκδοση","Οι κινεζικές, ευρωπαϊκές και αμερικανικές εκδόσεις διαφέρουν σε μπαταρία, προμηθευτές και εξοπλισμό.","https://www.tesla.com/model3"),
    Car("Tesla","Model Y","2020–2026","Αμερική / Ευρώπη / Κίνα / Ασία","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρες","299–534 hp (ανά έκδοση)","ανά έκδοση","1-speed","RWD / AWD","—","4.751 × 1.921 × 1.624 mm (ενδεικτικά)","2.890 mm","1.900–2.100 kg","περίπου 60–81 kWh ανά έκδοση","περίπου 455–600 km WLTP ανά έκδοση","3,7–6,9 s","ανά έκδοση","Οι προδιαγραφές αλλάζουν ανά εργοστάσιο, έτος, μπαταρία και αγορά.","https://www.tesla.com/modely"),
    Car("Ford","F-150 (14th Gen)","2021–2026","Αμερική / Μέση Ανατολή","Βενζίνη / Hybrid / EV","2.7 / 3.5 / 5.0 λίτρα ή ηλεκτρικό","325–720 hp","ανά έκδοση","10AT / 1-speed EV","RWD / 4WD","ανά έκδοση","5.890–6.200 mm μήκος, ανά καμπίνα","ανά έκδοση","2.000–3.200 kg","EV ανά έκδοση","ανά έκδοση","ανά έκδοση","ανά έκδοση","Pickup πλήρους μεγέθους· πολλές καμπίνες, μεταξόνια και ωφέλιμα φορτία.","https://www.ford.com/trucks/f150/"),
    Car("Ford","Mustang Mach-E","2021–2026","Αμερική / Ευρώπη","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρες","269–480 hp","ανά έκδοση","1-speed","RWD / AWD","—","4.713 × 1.881 × 1.624 mm","2.984 mm","1.950–2.250 kg","72–91 kWh usable περίπου","έως περίπου 600 km WLTP ανά έκδοση","3,5–7 s","ανά έκδοση","Η αυτονομία εξαρτάται από μπαταρία, τροχούς και κίνηση.","https://www.ford.com/suvs-crossovers/mach-e/"),
    Car("Chevrolet","Equinox EV","2024–2026","Αμερική / Καναδάς","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρας","213–300 hp","ανά έκδοση","1-speed","FWD / AWD","—","4.845 × 1.913 × 1.644 mm (ενδεικτικά)","2.954 mm","2.100–2.300 kg","περίπου 85 kWh","EPA έως περίπου 513 km ανά έκδοση","ανά έκδοση","ανά έκδοση","Βορειοαμερικανικό μοντέλο· οι EPA τιμές δεν συγκρίνονται απευθείας με WLTP.","https://www.chevrolet.com/"),
    Car("BYD","Atto 3 / Yuan Plus","2022–2026","Κίνα / Ευρώπη / Ασία / Αυστραλία","Αμιγώς ηλεκτρικό","Μόνιμου μαγνήτη, εμπρός","204 hp","310 Nm","1-speed","FWD","—","4.455 × 1.875 × 1.615 mm","2.720 mm","1.680–1.750 kg","49,9 ή 60,5 kWh LFP","περίπου 345–420 km WLTP ανά έκδοση","7,3 s","160 km/h","Η ονομασία και η πιστοποίηση αυτονομίας διαφέρουν ανά αγορά.","https://www.byd.com/"),
    Car("BYD","Seal","2023–2026","Κίνα / Ευρώπη / Ασία","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρες","313–530 hp","360–670 Nm","1-speed","RWD / AWD","—","4.800 × 1.875 × 1.460 mm","2.920 mm","1.920–2.200 kg","82,5 kWh LFP/Blade","περίπου 520–570 km WLTP ανά έκδοση","3,8–5,9 s","180 km/h","Υπάρχουν διαφορετικές μπαταρίες και ισχείς ανά αγορά και έτος.","https://www.byd.com/"),
    Car("BYD","Dolphin","2023–2026","Κίνα / Ευρώπη / Ασία / Λατινική Αμερική","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρας εμπρός","95–204 hp","180–310 Nm","1-speed","FWD","—","4.290 × 1.770 × 1.570 mm (ευρωπαϊκό, ενδεικτικά)","2.700 mm","1.500–1.700 kg","44,9 ή 60,4 kWh LFP","περίπου 340–427 km WLTP","7,0–12,3 s","150–160 km/h","Οι κινεζικές και ευρωπαϊκές εκδόσεις έχουν διαφορετικές μπαταρίες και διαστάσεις.","https://www.byd.com/"),
    Car("BYD","Song Plus / Seal U","2020–2026","Κίνα / Ευρώπη / Ασία / Λατινική Αμερική","Plug-in Hybrid / EV","1.5 βενζίνη + ηλεκτροκινητήρας ή EV","ανά έκδοση","ανά έκδοση","E-CVT / 1-speed","FWD / AWD ανά έκδοση","ανά αγορά","4.775 × 1.890 × 1.670 mm (ενδεικτικά)","2.765 mm","1.800–2.100 kg","PHEV 18,3–26,6 kWh ή EV","ηλεκτρική αυτονομία ανά έκδοση","ανά έκδοση","ανά έκδοση","Το Song Plus και το Seal U είναι συγγενικά αλλά η ονομασία/ρύθμιση διαφέρει ανά χώρα.","https://www.byd.com/"),
    Car("Geely","Galaxy E5 / EX5","2024–2026","Κίνα / διεθνείς αγορές","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρας εμπρός","218 hp","320 Nm","1-speed","FWD","—","4.615 × 1.901 × 1.670 mm","2.750 mm","περίπου 1.700 kg","49,5 ή 60,2 kWh LFP","ανά κύκλο και έκδοση","περίπου 6,9 s","ανά αγορά","Οι κινεζικές και εξαγωγικές προδιαγραφές/ονομασίες μπορεί να διαφέρουν.","https://global.geely.com/"),
    Car("Xiaomi","SU7","2024–2026","Κίνα","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρες","299–1.548 hp (ανά έκδοση)","400–1.770 Nm","1-speed","RWD / AWD","—","4.997 × 1.963 × 1.455 mm","3.000 mm","1.980–2.200 kg","73,6–101 kWh ανά έκδοση","CLTC έως περίπου 800 km (όχι WLTP)","1,98–5,3 s","ανά έκδοση","Οι κινεζικές τιμές CLTC δεν είναι άμεσα συγκρίσιμες με WLTP/EPA.","https://www.mi.com/"),
    Car("MG","MG4 Electric","2022–2026","Κίνα / Ευρώπη / Ασία / Αυστραλία","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρας πίσω","170–435 hp","250–600 Nm","1-speed","RWD / AWD (XPower)","—","4.287 × 1.836 × 1.504 mm","2.705 mm","1.650–1.850 kg","51–77 kWh ανά έκδοση","350–520 km WLTP περίπου","3,8–7,9 s","ανά έκδοση","Οι μπαταρίες και η γκάμα αλλάζουν ανά έτος και αγορά.","https://www.mgmotor.eu/"),
    Car("Tata","Nexon / Nexon EV","2020–2026","Ινδία","Βενζίνη / Diesel / EV","1.2 turbo petrol, 1.5 diesel ή ηλεκτρικό","100–145 hp","ανά έκδοση","MT / AMT / DCT / 1-speed EV","FWD","ανά έκδοση","3.995 × 1.804 × 1.620 mm","2.498 mm","1.200–1.500 kg","EV περίπου 30–45 kWh ανά γενιά","ανά έκδοση, κύκλος MIDC/ARAI","ανά έκδοση","ανά έκδοση","Πολύ σημαντικό ινδικό μοντέλο· το Nexon EV έχει αναβαθμιστεί σε μπαταρία και αυτονομία.","https://cars.tatamotors.com/"),
    Car("Mahindra","XUV700","2021–2026","Ινδία / εξαγωγές επιλεγμένες","Βενζίνη / Diesel","2.0 turbo petrol / 2.2 diesel","155–200 hp","360–450 Nm","6MT / 6AT","FWD / AWD (επιλεγμένες)","ανά έκδοση","4.695 × 1.890 × 1.755 mm","2.750 mm","1.700–1.900 kg","—","—","ανά έκδοση","ανά έκδοση","SUV 5/7 θέσεων· οι εκδόσεις και τα συστήματα ασφαλείας διαφέρουν ανά εξοπλισμό.","https://auto.mahindra.com/"),
    Car("Maruti Suzuki","Grand Vitara","2022–2026","Ινδία","Βενζίνη / Strong Hybrid / CNG","1.5 λίτρα","87–103 hp","ανά έκδοση","5MT / 6AT / e-CVT","FWD / AWD (mild hybrid)","4,4–6,0 l/100 km (ανά έκδοση)","4.345 × 1.795 × 1.645 mm","2.600 mm","1.150–1.300 kg","—","—","ανά έκδοση","ανά έκδοση","Ινδικό SUV με mild/strong hybrid και CNG σε επιλεγμένες εκδόσεις.","https://www.marutisuzuki.com/"),
    Car("Chery","Tiggo 8 Pro","2021–2026","Κίνα / Μέση Ανατολή / Ασία / Λατινική Αμερική","Βενζίνη / Plug-in Hybrid","1.6T ή 2.0T / PHEV ανά αγορά","186–600+ hp (PHEV υψηλών επιδόσεων, ανά έκδοση)","ανά έκδοση","7DCT / 8AT / υβριδικό","FWD / AWD","ανά αγορά","4.722 × 1.860 × 1.746 mm (ενδεικτικά)","2.710 mm","1.600–2.000 kg","PHEV ανά έκδοση","ηλεκτρική αυτονομία ανά αγορά","ανά έκδοση","ανά έκδοση","Η οικογένεια Tiggo 8 έχει πολλές εκδόσεις και διαφορετικά ονόματα ανά αγορά.","https://www.cheryinternational.com/"),
    Car("Great Wall Motor","Haval H6","2020–2026","Κίνα / Αυστραλία / Ασία / Μέση Ανατολή","Βενζίνη / Hybrid / PHEV","1.5T ή 2.0T","150–240 hp","ανά έκδοση","7DCT / 9DCT / υβριδικό","FWD / AWD","ανά αγορά","4.653 × 1.886 × 1.730 mm (ενδεικτικά)","2.738 mm","1.550–1.850 kg","PHEV ανά έκδοση","ανά κύκλο και αγορά","ανά έκδοση","ανά έκδοση","Διαφορετικές γενιές, κινητήρες και συστήματα κίνησης σε κάθε ήπειρο.","https://www.gwm-global.com/"),
    Car("NIO","ET5","2022–2026","Κίνα / Ευρώπη επιλεγμένες αγορές","Αμιγώς ηλεκτρικό","Δύο ηλεκτροκινητήρες","490 hp","700 Nm","1-speed","AWD","—","4.790 × 1.960 × 1.499 mm","2.888 mm","περίπου 2.200 kg","75 ή 100 kWh (ανταλλάξιμη)","ανά κύκλο και μπαταρία","4,0 s","200 km/h","Η δυνατότητα battery swap και οι προδιαγραφές διαφέρουν ανά χώρα.","https://www.nio.com/"),
    Car("XPeng","G6","2023–2026","Κίνα / Ευρώπη / Ασία","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρες","258–476 hp","440–660 Nm","1-speed","RWD / AWD","—","4.753 × 1.920 × 1.650 mm","2.890 mm","1.990–2.200 kg","66–87,5 kWh ανά έκδοση","έως περίπου 570 km WLTP (ανά έκδοση)","4,0–6,9 s","ανά έκδοση","Οι κινεζικές και ευρωπαϊκές εκδόσεις διαφέρουν σε μπαταρία, ισχύ και πιστοποίηση.","https://www.xpeng.com/"),
    Car("Li Auto","L6","2024–2026","Κίνα","Range-Extended EV (EREV)","1.5 turbo generator + ηλεκτροκινητήρες","408 hp","529 Nm","1-speed","AWD","—","4.925 × 1.960 × 1.735 mm","2.920 mm","περίπου 2.330 kg","36,8 kWh","ηλεκτρική αυτονομία CLTC και συνολική ανά έκδοση","5,4 s","180 km/h","Το θερμικό μοτέρ λειτουργεί ως γεννήτρια· οι κινεζικές τιμές CLTC δεν ισοδυναμούν με WLTP.","https://www.lixiang.com/"),
    Car("Mazda","CX-5","2020–2026","Ιαπωνία / Ευρώπη / Αμερική / Ασία","Βενζίνη / Diesel","2.0 / 2.5 petrol, 2.2 diesel ανά αγορά","150–256 hp","ανά έκδοση","6MT / 6AT","FWD / AWD","ανά αγορά","4.575 × 1.845 × 1.680 mm (ενδεικτικά)","2.700 mm","1.500–1.800 kg","—","—","ανά έκδοση","ανά έκδοση","Η γκάμα κινητήρων διαφέρει σημαντικά ανά χώρα· δεν υπάρχει ίδια diesel επιλογή παντού.","https://www.mazda.com/"),
    Car("Subaru","Outback","2020–2026","Αμερική / Ιαπωνία / Αυστραλία / Ευρώπη επιλεγμένες","Βενζίνη","2.5 boxer ή 2.4 turbo ανά αγορά","169–260 hp","ανά έκδοση","CVT","AWD","ανά αγορά","4.870 × 1.875 × 1.675 mm (ενδεικτικά)","2.745 mm","1.650–1.850 kg","—","—","ανά έκδοση","ανά έκδοση","Η αμερικανική και η ιαπωνική γκάμα δεν ταυτίζονται με την ευρωπαϊκή.","https://www.subaru.com/"),
    Car("Lexus","RX","2023–2026","Αμερική / Ευρώπη / Ιαπωνία / Ασία","Hybrid / Plug-in Hybrid / Turbo Hybrid","2.4T / 2.5 hybrid / 2.4 PHEV","246–366 hp","ανά έκδοση","e-CVT / 6AT","FWD / AWD","ανά αγορά","4.890 × 1.920 × 1.695 mm","2.850 mm","1.900–2.200 kg","PHEV ανά έκδοση","ηλεκτρική αυτονομία ανά αγορά","ανά έκδοση","ανά έκδοση","RX 350, 350h, 450h+ και 500h έχουν διαφορετικά τεχνικά χαρακτηριστικά.","https://www.lexus.com/"),
    Car("Renault","Clio V facelift","2020–2026","Ευρώπη / Ασία / Λατινική Αμερική","Βενζίνη / LPG / Full Hybrid","1.0 TCe / 1.6 E-Tech hybrid","65–145 hp","ανά έκδοση","MT / CVT / multimode hybrid","FWD","ανά αγορά","4.050 × 1.798 × 1.440 mm (ενδεικτικά)","2.583 mm","1.100–1.300 kg","—","—","ανά έκδοση","ανά έκδοση","Το facelift και η γκάμα κινητήρων αλλάζουν ανά έτος και αγορά.","https://www.renault.com/"),
    Car("Peugeot","3008 (3rd Gen)","2024–2026","Ευρώπη / Ασία επιλεγμένες αγορές","Hybrid / Plug-in Hybrid / EV","1.2 mild hybrid / 1.6 PHEV / ηλεκτρικό","136–325 hp","ανά έκδοση","e-DCS6 / αυτόματο / 1-speed EV","FWD / AWD (EV Dual Motor)","ανά έκδοση","4.542 × 1.895 × 1.641 mm","2.739 mm","1.700–2.200 kg","EV περίπου 73 ή 98 kWh","έως περίπου 700 km WLTP (Long Range)","ανά έκδοση","ανά έκδοση","Νέα γενιά με mild hybrid, plug-in και E-3008· οι προδιαγραφές διαφέρουν.","https://www.peugeot.com/"),
    Car("Skoda","Kodiaq (2nd Gen)","2024–2026","Ευρώπη / Ασία","Βενζίνη / Diesel / PHEV","1.5 eTSI / 2.0 TSI / 2.0 TDI","150–204 hp","ανά έκδοση","7DSG","FWD / AWD","ανά έκδοση","4.758 × 1.864 × 1.659 mm","2.791 mm","1.650–1.950 kg","PHEV περίπου 25,7 kWh","ηλεκτρική αυτονομία WLTP ανά έκδοση","ανά έκδοση","ανά έκδοση","5 ή 7 θέσεων· η PHEV έκδοση είναι κυρίως προσθιοκίνητη.","https://www.skoda-auto.com/"),
    Car("Volvo","EX30","2024–2026","Ευρώπη / Αμερική / Ασία","Αμιγώς ηλεκτρικό","Single / Twin Motor","272–428 hp","343–543 Nm","1-speed","RWD / AWD","—","4.233 × 1.837 × 1.555 mm","2.650 mm","1.830–1.960 kg","51 ή 69 kWh","344–476 km WLTP περίπου","3,6–5,7 s","180 km/h","Οι εκδόσεις και οι μπαταρίες διαφέρουν ανά αγορά και έτος.","https://www.volvocars.com/"),
    Car("Lucid","Air","2022–2026","Αμερική / Μέση Ανατολή / Ευρώπη επιλεγμένες","Αμιγώς ηλεκτρικό","Dual / tri motor","430–1.234 hp","ανά έκδοση","1-speed","RWD / AWD","—","4.975 × 1.939 × 1.410 mm","2.960 mm","2.050–2.400 kg","84–118 kWh ανά έκδοση","EPA έως περίπου 830 km (έκδοση/έτος)","2,0–4,5 s","ανά έκδοση","Οι αμερικανικές EPA τιμές δεν συγκρίνονται απευθείας με WLTP.","https://lucidmotors.com/"),
    Car("Rivian","R1S","2022–2026","Αμερική / Καναδάς","Αμιγώς ηλεκτρικό","Dual / tri / quad motor","533–1.025 hp","ανά έκδοση","1-speed","AWD","—","5.100 × 2.015 × 1.970 mm (ενδεικτικά)","3.075 mm","2.700–3.200 kg","ανά έκδοση, περίπου 92–141 kWh","EPA ανά μπαταρία/έτος","2,6–4,5 s","ανά έκδοση","Μεγάλο ηλεκτρικό SUV 7 θέσεων· οι προδιαγραφές αλλάζουν ανά γενιά και μπαταρία.","https://rivian.com/"),
    Car("Suzuki","Swift (4th Gen)","2024–2026","Ιαπωνία / Ευρώπη / Ινδία / Ασία","Βενζίνη / Mild Hybrid","1.2 λίτρα 3κύλινδρο","82–82 hp περίπου (ανά αγορά)","112 Nm περίπου","5MT / CVT / AGS","FWD / AWD (Ιαπωνία)","ανά αγορά","3.860 × 1.735 × 1.495 mm (ενδεικτικά)","2.450 mm","περίπου 950–1.050 kg","—","—","ανά έκδοση","ανά έκδοση","Η ινδική, ιαπωνική και ευρωπαϊκή έκδοση έχουν διαφορές σε εξοπλισμό και ρύπους.","https://www.globalsuzuki.com/"),
    Car("Toyota","Hilux (8th Gen facelift)","2020–2026","Ασία / Ευρώπη / Αυστραλία / Αφρική / Λατινική Αμερική","Diesel / Βενζίνη","2.4 / 2.8 diesel, 2.7 petrol ανά αγορά","150–204 hp","ανά έκδοση","6MT / 6AT","RWD / 4WD","ανά έκδοση","5.325 × 1.855 × 1.815 mm (double cab, ενδεικτικά)","3.085 mm","1.900–2.200 kg","—","—","ανά έκδοση","ανά έκδοση","Pickup με διαφορετικά μοτέρ, φορτία και ομολογκασιόν ανά χώρα.","https://www.toyota-global.com/"),
    Car("Isuzu","D-Max","2020–2026","Ασία / Ευρώπη / Αυστραλία / Αφρική","Diesel","1.9 ή 3.0 turbo diesel","150–190 hp","350–450 Nm","6MT / 6AT","RWD / 4WD","ανά αγορά","5.265 × 1.870 × 1.790 mm (ενδεικτικά)","3.125 mm","1.900–2.200 kg","—","—","ανά έκδοση","ανά έκδοση","Διαφορές σε κινητήρες και προδιαγραφές ανά περιοχή και έτος.","https://www.isuzu.co.jp/world/"),
    Car("Dacia","Duster (3rd Gen)","2024–2026","Ευρώπη / Βόρεια Αφρική / Μέση Ανατολή","LPG / Mild Hybrid / Full Hybrid","1.0 ECO-G / 1.2 mild hybrid / 1.6 hybrid","100–140 hp","ανά έκδοση","6MT / automatic hybrid","FWD / 4WD","ανά έκδοση","4.343 × 1.813 × 1.656 mm","2.657 mm","1.300–1.600 kg","—","—","ανά έκδοση","ανά έκδοση","Νέα γενιά με διαφορετικά σύνολα κίνησης και διαθεσιμότητα ανά χώρα.","https://www.dacia.com/"),
    Car("BYD","Seagull / Dolphin Mini","2023–2026","Κίνα / Λατινική Αμερική / Ασία","Αμιγώς ηλεκτρικό","Ηλεκτροκινητήρας εμπρός","75–102 hp","135–180 Nm","1-speed","FWD","—","3.780 × 1.715 × 1.580 mm (ενδεικτικά)","2.500 mm","1.160–1.240 kg","30,1 ή 38,9 kWh LFP","CLTC περίπου 305–405 km","ανά έκδοση","130 km/h","Η ονομασία και η αυτονομία αλλάζουν ανά αγορά· CLTC δεν ισούται με WLTP.","https://www.byd.com/"),
    // Μοντέλο νέας γενιάς· στοιχεία που δεν έχουν δημοσιευθεί παραμένουν ρητά μη επιβεβαιωμένα.
    Car("Hyundai","Tucson (5η γενιά MY2027)","2027–","Αμερική / ΗΠΑ (παγκόσμια πρεμιέρα· λανσάρισμα ΗΠΑ αρχές 2027)","Βενζίνη / Hybrid (λεπτομέρειες εκδόσεων μη επιβεβαιωμένες)","Νέο hybrid system· κωδικός και κυβισμός θερμικού κινητήρα μη επιβεβαιωμένα","μη επιβεβαιωμένο","μη επιβεβαιωμένο","μη επιβεβαιωμένο","μη επιβεβαιωμένο","μη επιβεβαιωμένο (EPA: δεν έχει δημοσιευθεί)","περίπου 4.699 × 1.905 mm (μήκος × πλάτος· ύψος μη επιβεβαιωμένο)","περίπου 2.784 mm (109,6 in)","μη επιβεβαιωμένο","μη επιβεβαιωμένο","μη επιβεβαιωμένο (EPA: δεν έχει δημοσιευθεί)","μη επιβεβαιωμένο","μη επιβεβαιωμένο","Νέα 5η γενιά C-SUV. Η Hyundai αναφέρει λανσάρισμα στις ΗΠΑ στις αρχές 2027. Οι εικόνες/προδιαγραφές προπαραγωγής εκτός ΗΠΑ ενδέχεται να διαφέρουν από τα τελικά αμερικανικά οχήματα. Τα αναλυτικά specs δεν έχουν ανακοινωθεί ακόμη.","https://www.hyundaiusa.com/us/en/vehicles/2027-tucson"),
)

class CarMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CarTrackerApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarTrackerApp() {
    var query by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("Όλες οι αγορές") }
    var fuel by remember { mutableStateOf("Όλα τα καύσιμα") }
    var selected by remember { mutableStateOf<Car?>(null) }
    val regions = listOf("Όλες οι αγορές", "Ευρώπη", "Αμερική", "Κίνα", "Ιαπωνία", "Κορέα", "Ινδία", "Παγκόσμια")
    val fuels = listOf("Όλα τα καύσιμα", "Βενζίνη", "Diesel", "Hybrid", "Plug-in Hybrid", "Ηλεκτρικό")
    val filtered = cars.filter {
        val text = "${it.make} ${it.model} ${it.years} ${it.market} ${it.engine} ${it.fuel}".lowercase()
        text.contains(query.lowercase()) &&
            (region == "Όλες οι αγορές" || it.market.contains(region, true) || (region == "Παγκόσμια" && it.market.contains("Παγκόσμια", true))) &&
            (fuel == "Όλα τα καύσιμα" || it.fuel.contains(fuel, true) ||
                (fuel == "Hybrid" && it.fuel.contains("Hybrid", true)) ||
                (fuel == "Ηλεκτρικό" && it.fuel.contains("ηλεκτρικό", true)))
    }

    MaterialTheme(colorScheme = lightColorScheme(primary = androidx.compose.ui.graphics.Color(0xFF174A73))) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("GLOBAL CAR TRACKER", fontWeight = FontWeight.Bold)
                            Text("Παγκόσμιος κατάλογος αυτοκινήτων 2020+", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                )
            }
        ) { padding ->
            Column(Modifier.fillMaxSize().padding(padding).padding(12.dp)) {
                OutlinedTextField(
                    value = query, onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Αναζήτηση μάρκας, μοντέλου, κινητήρα…") },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                Text("Αγορά / περιοχή", fontWeight = FontWeight.SemiBold)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Όλες οι αγορές", "Ευρώπη", "Αμερική", "Κίνα").forEach { item ->
                        FilterChip(selected = region == item, onClick = { region = item }, label = { Text(item, maxLines = 1) })
                    }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Ιαπωνία", "Κορέα", "Ινδία").forEach { item ->
                        FilterChip(selected = region == item, onClick = { region = item }, label = { Text(item) })
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text("Κίνηση / καύσιμο", fontWeight = FontWeight.SemiBold)
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    listOf("Όλα τα καύσιμα", "Βενζίνη", "Diesel").forEach { item ->
                        FilterChip(selected = fuel == item, onClick = { fuel = item }, label = { Text(item, maxLines = 1) })
                    }
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    listOf("Hybrid", "Plug-in Hybrid", "Ηλεκτρικό").forEach { item ->
                        FilterChip(selected = fuel == item, onClick = { fuel = item }, label = { Text(item, maxLines = 1) })
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text("Βρέθηκαν ${filtered.size} καταχωρήσεις • ενδεικτικός αρχικός κατάλογος", style = MaterialTheme.typography.labelMedium)
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(bottom = 24.dp)) {
                    items(filtered) { car ->
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().clickable { selected = car },
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Column(Modifier.padding(14.dp)) {
                                Text("${car.make} ${car.model}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Text("${car.years}  •  ${car.market}", style = MaterialTheme.typography.bodySmall)
                                Spacer(Modifier.height(5.dp))
                                Text("${car.fuel}  |  ${car.power}  |  ${car.transmission}", style = MaterialTheme.typography.bodyMedium)
                                Text("Κινητήρας: ${car.engine}   •   Κίνηση: ${car.drive}", style = MaterialTheme.typography.bodySmall)
                                Text("Πάτησε για όλα τα τεχνικά στοιχεία", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            }
                        }
                    }
                }
            }
        }
    }

    selected?.let { car ->
        AlertDialog(
            onDismissRequest = { selected = null },
            confirmButton = { TextButton(onClick = { selected = null }) { Text("Κλείσιμο") } },
            title = { Text("${car.make} ${car.model}") },
            text = {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.heightIn(max = 520.dp)) {
                    val fields = listOf(
                        "Έτη / γενιά" to car.years, "Αγορές" to car.market, "Καύσιμο" to car.fuel,
                        "Κινητήρας" to car.engine, "Ισχύς" to car.power, "Ροπή" to car.torque,
                        "Κιβώτιο" to car.transmission, "Κίνηση" to car.drive, "Κατανάλωση" to car.consumption,
                        "Διαστάσεις" to car.dimensions, "Μεταξόνιο" to car.wheelbase, "Βάρος" to car.weight,
                        "Μπαταρία" to car.battery, "Αυτονομία" to car.range, "0–100 km/h" to car.acceleration,
                        "Τελική ταχύτητα" to car.topSpeed, "Σημειώσεις" to car.notes
                    )
                    items(fields) { field ->
                        Column {
                            Text(field.first, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                            Text(field.second, style = MaterialTheme.typography.bodyMedium)
                            HorizontalDivider()
                        }
                    }
                    item {
                        TextButton(onClick = { /* URL is shown below for manual source verification */ }) {
                            Text("Πηγή κατασκευαστή: ${car.source}")
                        }
                    }
                }
            }
        )
    }
}
