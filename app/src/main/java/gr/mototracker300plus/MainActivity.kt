package gr.mototracker300plus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Motorcycle(
    val make: String,
    val model: String,
    val years: String,
    val power: String,
    val engine: String,
    val consumption: String,
    val notes: String
)

private val motorcycles = listOf(
    Motorcycle("KTM", "1290 Super Adventure S", "2021–2023", "160 hp", "1301 cc V-twin", "5.5–6.5 l/100 km", "Ισχυρή adventure. Έλεγχος ιστορικού service, ψύξης, ηλεκτρονικών και αναρτήσεων."),
    Motorcycle("Honda", "Africa Twin 1100", "2020–2024", "102 hp", "1084 cc twin", "4.8–5.5 l/100 km", "Γενικά αξιόπιστη. Έλεγχος DCT όπου υπάρχει, συμπλέκτη, ακτινών και ιστορικού συντήρησης."),
    Motorcycle("Suzuki", "V-Strom 1050 XT", "2020–2023", "107 hp", "1037 cc V-twin", "5.0–6.0 l/100 km", "Άνετη και απλή στην καθημερινή χρήση. Έλεγχος αναρτήσεων, φρένων και μετάδοσης."),
    Motorcycle("BMW", "R 1250 GS", "2019–2023", "136 hp", "1254 cc boxer", "5.0–6.0 l/100 km", "Έλεγχος service, διαρροών, cardan, ESA και ηλεκτρονικών συστημάτων."),
    Motorcycle("Yamaha", "Tracer 9 GT", "2021–2025", "119 hp", "890 cc triple", "5.0–5.8 l/100 km", "Sport touring με έμφαση στην ευελιξία. Έλεγχος quickshifter, αναρτήσεων και service.")
)

data class NewMotorcycle(
    val make: String,
    val model: String,
    val cc: Int,
    val year: Int,
    val category: String,
    val status: String,
    val details: String,
    val ccExact: String = cc.toString()
)

// Νέα μοντέλα/νέες πλατφόρμες που παρουσιάστηκαν ή μπήκαν στην αγορά την περίοδο 2016–2026.
// Δεν περιλαμβάνονται απλές αλλαγές χρωμάτων. Το έτος είναι το πρώτο έτος παρουσίασης/εμπορικής διάθεσης,
// το οποίο μπορεί να διαφέρει ανά αγορά. Τα ηλεκτρικά μοντέλα καταγράφονται μόνο όταν η ισχύς τους
// αντιστοιχεί σε κατηγορία άνω των 300 cc.
private val newMotorcycles = listOf(
    NewMotorcycle("Aprilia","RS 660",659,2020,"Sport","Παραγωγής","Δικύλινδρη sport μεσαίου κυβισμού."),
    NewMotorcycle("Aprilia","Tuono 660",659,2021,"Naked","Παραγωγής","Γυμνή sport έκδοση της πλατφόρμας 660."),
    NewMotorcycle("Aprilia","Tuareg 660",659,2022,"Adventure","Παραγωγής","Μεσαία adventure με χωμάτινο προσανατολισμό."),
    NewMotorcycle("Aprilia","RS 457",457,2023,"Sport","Παραγωγής","Δικύλινδρη sport A2."),
    NewMotorcycle("Aprilia","Tuono 457",457,2025,"Naked","Παραγωγής","Naked έκδοση της πλατφόρμας 457."),
    NewMotorcycle("Aprilia","RSV4 1100",1099,2019,"Superbike","Παραγωγής","Εξέλιξη της superbike RSV4 με αυξημένο κυβισμό."),
    NewMotorcycle("Benda","LFC700",700,2021,"Cruiser","Παραγωγής","Μεγάλο τετρακύλινδρο cruiser με ιδιαίτερη σχεδίαση."),
    NewMotorcycle("Benda","Napoleon 500",475,2023,"Cruiser","Παραγωγής","V-twin cruiser με bobber αισθητική."),
    NewMotorcycle("Benda","Dark Flag 500",496,2023,"Cruiser","Παραγωγής","V4 cruiser μεσαίου κυβισμού."),
    NewMotorcycle("Benelli","TRK 502",500,2016,"Adventure Touring","Παραγωγής","Δικύλινδρη adventure/touring πλατφόρμα."),
    NewMotorcycle("Benelli","Leoncino 500",500,2017,"Scrambler","Παραγωγής","Modern classic με δικύλινδρο κινητήρα."),
    NewMotorcycle("Benelli","752S",754,2018,"Naked","Παραγωγής","Μεσαία naked με δικύλινδρο κινητήρα."),
    NewMotorcycle("Benelli","TRK 702",698,2023,"Adventure Touring","Παραγωγής","Νεότερη μεσαία adventure της σειράς TRK."),
    NewMotorcycle("Benelli","TRK 702X",698,2023,"Adventure","Παραγωγής","Πιο χωμάτινη έκδοση της TRK 702."),
    NewMotorcycle("Benelli","Tornado 550",554,2024,"Sport","Παραγωγής","Νέα sport πλατφόρμα με δικύλινδρο κινητήρα."),
    NewMotorcycle("BMW","R nineT Scrambler",1170,2016,"Heritage","Παραγωγής","Scrambler έκδοση της οικογένειας R nineT."),
    NewMotorcycle("BMW","K 1600 B",1649,2017,"Bagger","Παραγωγής","Bagger εξακύλινδρης πλατφόρμας K 1600."),
    NewMotorcycle("BMW","F 850 GS",853,2018,"Adventure","Παραγωγής","Νέα γενιά μεσαίας adventure της BMW."),
    NewMotorcycle("BMW","F 850 GS Adventure",853,2019,"Adventure","Παραγωγής","Μεγαλύτερη αυτονομία και touring εξοπλισμός."),
    NewMotorcycle("BMW","S 1000 XR",999,2020,"Sport Touring","Παραγωγής","Νέα γενιά sport touring με τετρακύλινδρο κινητήρα."),
    NewMotorcycle("BMW","F 900 R",895,2020,"Naked","Παραγωγής","Μεσαία naked με δικύλινδρο κινητήρα."),
    NewMotorcycle("BMW","F 900 XR",895,2020,"Sport Touring","Παραγωγής","Sport touring έκδοση της πλατφόρμας F 900."),
    NewMotorcycle("BMW","R 18",1802,2020,"Cruiser","Παραγωγής","Μεγάλο boxer cruiser."),
    NewMotorcycle("BMW","M 1000 RR",999,2021,"Superbike","Παραγωγής","Υψηλών επιδόσεων homologation superbike."),
    NewMotorcycle("BMW","R 1300 GS",1300,2023,"Adventure","Παραγωγής","Νέα γενιά μεγάλης boxer adventure."),
    NewMotorcycle("BMW","F 900 GS",895,2024,"Adventure","Παραγωγής","Πιο ελαφριά και χωμάτινη νέα F GS."),
    NewMotorcycle("BMW","R 12",1170,2024,"Heritage","Παραγωγής","Νέα cruiser/roadster πλατφόρμα boxer."),
    NewMotorcycle("BMW","R 12 G/S",1170,2025,"Adventure Heritage","Παραγωγής","Heritage enduro εμπνευσμένη από την R 80 G/S."),
    NewMotorcycle("BMW","R 1300 RT",1300,2025,"Touring","Παραγωγής","Νέα γενιά μεγάλου touring boxer."),
    NewMotorcycle("BMW","F 450 GS",420,2026,"Adventure","Παρουσιάστηκε","Δικύλινδρη adventure κατηγορίας A2."),
    NewMotorcycle("BSA","Gold Star 650",652,2022,"Classic","Παραγωγής","Επιστροφή του ιστορικού ονόματος Gold Star."),
    NewMotorcycle("CFMoto","650MT",649,2017,"Adventure Touring","Παραγωγής","Μεσαία δικύλινδρη adventure/touring."),
    NewMotorcycle("CFMoto","700CL-X",693,2021,"Modern Classic","Παραγωγής","Neo-retro πλατφόρμα σε εκδόσεις Heritage και Sport."),
    NewMotorcycle("CFMoto","800MT",799,2021,"Adventure Touring","Παραγωγής","Δικύλινδρη adventure μεσαίου/μεγάλου κυβισμού."),
    NewMotorcycle("CFMoto","800NK",799,2023,"Naked","Παραγωγής","Naked μοντέλο με δικύλινδρο κινητήρα."),
    NewMotorcycle("CFMoto","450SR",449,2023,"Sport","Παραγωγής","Δικύλινδρη sport μεσαίας κατηγορίας."),
    NewMotorcycle("CFMoto","450MT",449,2024,"Adventure","Παραγωγής","Ελαφριά adventure με δικύλινδρο κινητήρα."),
    NewMotorcycle("CFMoto","700MT",693,2024,"Adventure Touring","Παραγωγής","Μεσαία touring adventure."),
    NewMotorcycle("CFMoto","675SR-R",675,2025,"Sport","Παραγωγής","Τρικύλινδρη sport μοτοσυκλέτα."),
    NewMotorcycle("CFMoto","800MT-X",799,2025,"Adventure","Παραγωγής","Πιο χωμάτινη έκδοση της 800MT."),
    NewMotorcycle("Ducati","Multistrada 950",937,2017,"Adventure Touring","Παραγωγής","Μεσαία Multistrada με Testastretta L-twin."),
    NewMotorcycle("Ducati","Scrambler 1100",1079,2018,"Scrambler","Παραγωγής","Μεγάλη Scrambler με αερόψυκτο L-twin."),
    NewMotorcycle("Ducati","Panigale V4",1103,2018,"Superbike","Παραγωγής","Νέα V4 superbike που αντικατέστησε την 1299."),
    NewMotorcycle("Ducati","Diavel 1260",1262,2019,"Power Cruiser","Παραγωγής","Νέα γενιά Diavel με Testastretta DVT."),
    NewMotorcycle("Ducati","Streetfighter V4",1103,2020,"Hyper Naked","Παραγωγής","Γυμνή V4 υψηλών επιδόσεων."),
    NewMotorcycle("Ducati","Multistrada V4",1158,2021,"Adventure Touring","Παραγωγής","Νέα V4 γενιά με ραντάρ σε επιλεγμένες εκδόσεις."),
    NewMotorcycle("Ducati","DesertX",937,2022,"Adventure","Παραγωγής","Adventure με τροχό 21 ιντσών και off-road προσανατολισμό."),
    NewMotorcycle("Ducati","Diavel V4",1158,2023,"Power Cruiser","Παραγωγής","V4 Granturismo κινητήρας σε power cruiser."),
    NewMotorcycle("Ducati","Scrambler 800 (3rd Gen)",803,2023,"Scrambler","Παραγωγής","Νέα γενιά της οικογένειας Scrambler."),
    NewMotorcycle("Ducati","Hypermotard 698 Mono",659,2024,"Supermoto","Παραγωγής","Μονοκύλινδρη supermoto υψηλών επιδόσεων."),
    NewMotorcycle("Ducati","Multistrada V2",890,2025,"Adventure Touring","Παραγωγής","Νέα γενιά μεσαίας Multistrada."),
    NewMotorcycle("Ducati","Panigale V4 (new generation)",1103,2025,"Superbike","Παραγωγής","Νέα γενιά Panigale V4 με ανασχεδιασμό πλαισίου και αεροδυναμικής."),
    NewMotorcycle("Ducati","DesertX Discovery",937,2025,"Adventure","Παραγωγής","Εξοπλισμένη έκδοση της DesertX."),
    NewMotorcycle("Fantic","Caballero 500",449,2018,"Scrambler","Παραγωγής","Retro scrambler με μονοκύλινδρο κινητήρα."),
    NewMotorcycle("Fantic","Caballero 700",689,2023,"Scrambler","Παραγωγής","Δικύλινδρη scrambler μεσαίου κυβισμού."),
    NewMotorcycle("Harley-Davidson","Pan America 1250",1252,2021,"Adventure Touring","Παραγωγής","Πρώτη μεγάλη adventure της Harley-Davidson."),
    NewMotorcycle("Harley-Davidson","Sportster S",1252,2021,"Sport Cruiser","Παραγωγής","Νέα Revolution Max πλατφόρμα."),
    NewMotorcycle("Harley-Davidson","Nightster",975,2022,"Cruiser","Παραγωγής","Μεσαίο cruiser με Revolution Max 975."),
    NewMotorcycle("Honda","Africa Twin CRF1000L",998,2016,"Adventure","Παραγωγής","Επιστροφή της Africa Twin με δικύλινδρο εν σειρά κινητήρα."),
    NewMotorcycle("Honda","X-ADV",745,2017,"Crossover Scooter","Παραγωγής","Crossover scooter με DCT κιβώτιο."),
    NewMotorcycle("Honda","Rebel 500",471,2017,"Cruiser","Παραγωγής","Μεσαίο cruiser με δικύλινδρο κινητήρα."),
    NewMotorcycle("Honda","CB1000R",998,2018,"Naked","Παραγωγής","Neo Sports Café τετρακύλινδρη naked."),
    NewMotorcycle("Honda","Gold Wing",1833,2018,"Touring","Παραγωγής","Νέα γενιά εξακύλινδρης Gold Wing με προαιρετικό DCT."),
    NewMotorcycle("Honda","CB650R",649,2019,"Naked","Παραγωγής","Neo Sports Café τετρακύλινδρη naked."),
    NewMotorcycle("Honda","CBR650R",649,2019,"Sport","Παραγωγής","Sport μοντέλο με τετρακύλινδρο κινητήρα."),
    NewMotorcycle("Honda","Africa Twin CRF1100L",1084,2020,"Adventure","Παραγωγής","Νέα γενιά Africa Twin 1100, με χειροκίνητο ή DCT."),
    NewMotorcycle("Honda","NT1100",1084,2022,"Sport Touring","Παραγωγής","Touring μοντέλο με κινητήρα Africa Twin."),
    NewMotorcycle("Honda","CB750 Hornet",755,2023,"Naked","Παραγωγής","Νέα δικύλινδρη πλατφόρμα 755 cc."),
    NewMotorcycle("Honda","XL750 Transalp",755,2023,"Adventure","Παραγωγής","Μεσαία adventure με δικύλινδρο κινητήρα."),
    NewMotorcycle("Honda","CL500",471,2023,"Scrambler","Παραγωγής","A2 scrambler με βάση τον κινητήρα 500."),
    NewMotorcycle("Honda","NX500",471,2024,"Adventure Touring","Παραγωγής","Εξέλιξη της CB500X με νέο όνομα και αναβαθμίσεις."),
    NewMotorcycle("Honda","CB1000 Hornet",1000,2025,"Naked","Παραγωγής","Νέα μεγάλη naked τετρακύλινδρη."),
    NewMotorcycle("Honda","CB1000GT",1000,2025,"Sport Touring","Παρουσιάστηκε","Sport touring βασισμένη στην πλατφόρμα CB1000."),
    NewMotorcycle("Kawasaki","Z900",948,2017,"Naked","Παραγωγής","Νέα τετρακύλινδρη naked πλατφόρμα."),
    NewMotorcycle("Kawasaki","Ninja 1000SX",1043,2020,"Sport Touring","Παραγωγής","Εξέλιξη της Z1000SX με touring χαρακτήρα."),
    NewMotorcycle("Kawasaki","Z H2",998,2020,"Supercharged Naked","Παραγωγής","Υπερτροφοδοτούμενη naked με κινητήρα H2."),
    NewMotorcycle("Kawasaki","KLR650 (new generation)",652,2022,"Adventure","Παραγωγής","Επιστροφή/νέα γενιά της μονοκύλινδρης KLR650."),
    NewMotorcycle("Kawasaki","Eliminator 500",451,2023,"Cruiser","Παραγωγής","Νέο cruiser με δικύλινδρο κινητήρα."),
    NewMotorcycle("Kawasaki","Ninja ZX-4R",399,2023,"Sport","Παραγωγής","Τετρακύλινδρη sport 400 cc."),
    NewMotorcycle("Kawasaki","Ninja 500",451,2024,"Sport","Παραγωγής","Νέα δικύλινδρη sport μεσαίας κατηγορίας."),
    NewMotorcycle("Kawasaki","Z500",451,2024,"Naked","Παραγωγής","Naked έκδοση της πλατφόρμας 500."),
    NewMotorcycle("Kawasaki","Versys 1100",1099,2025,"Adventure Touring","Παραγωγής","Μεγαλύτερος κυβισμός και αναβαθμίσεις στη Versys."),
    NewMotorcycle("Kove","450 Rally",449,2022,"Rally","Παραγωγής","Rally replica με μονοκύλινδρο κινητήρα."),
    NewMotorcycle("Kove","800X Pro",799,2023,"Adventure","Παραγωγής","Δικύλινδρη adventure με έμφαση στο χώμα."),
    NewMotorcycle("Kove","800X Rally",799,2024,"Adventure Rally","Παραγωγής","Πιο αγωνιστική έκδοση της 800X."),
    NewMotorcycle("KTM","1290 Super Duke R",1301,2017,"Hyper Naked","Παραγωγής","Νέα γενιά της μεγάλης Super Duke."),
    NewMotorcycle("KTM","790 Duke",799,2018,"Naked","Παραγωγής","Δικύλινδρη μεσαία naked."),
    NewMotorcycle("KTM","790 Adventure",799,2019,"Adventure","Παραγωγής","Μεσαία adventure με δικύλινδρο LC8c."),
    NewMotorcycle("KTM","890 Duke R",889,2020,"Naked","Παραγωγής","Πιο σπορ έκδοση της 890 Duke."),
    NewMotorcycle("KTM","890 Adventure",889,2021,"Adventure","Παραγωγής","Νέα μεσαία adventure πλατφόρμα."),
    NewMotorcycle("KTM","1290 Super Adventure S",1301,2021,"Adventure","Παραγωγής","Νέα γενιά μεγάλης adventure με ραντάρ σε επιλεγμένες εκδόσεις."),
    NewMotorcycle("KTM","RC 8C",889,2021,"Track Sport","Περιορισμένη παραγωγή","Πίστα-only δικύλινδρη μοτοσυκλέτα περιορισμένης παραγωγής."),
    NewMotorcycle("KTM","1390 Super Duke R",1350,2024,"Hyper Naked","Παραγωγής","Νέα γενιά μεγάλης Super Duke."),
    NewMotorcycle("KTM","1390 Super Adventure S EVO",1350,2025,"Adventure","Παραγωγής","Νέα γενιά μεγάλης adventure με αυτοματοποιημένο κιβώτιο σε έκδοση EVO."),
    NewMotorcycle("Moto Guzzi","V85 TT",853,2019,"Adventure Touring","Παραγωγής","Νέα μεσαία adventure με εγκάρσιο V-twin."),
    NewMotorcycle("Moto Guzzi","V100 Mandello",1042,2022,"Sport Touring","Παραγωγής","Νέα υγρόψυκτη πλατφόρμα V100 με ενεργή αεροδυναμική."),
    NewMotorcycle("Moto Guzzi","Stelvio",1042,2024,"Adventure Touring","Παραγωγής","Μεγάλη adventure touring με κινητήρα V100."),
    NewMotorcycle("MV Agusta","Superveloce 800",798,2020,"Sport Heritage","Παραγωγής","Retro sport με τρικύλινδρο κινητήρα."),
    NewMotorcycle("MV Agusta","Lucky Explorer 9.5",931,2022,"Adventure","Παρουσιάστηκε ως concept","Adventure concept που εξελίχθηκε στη νεότερη Enduro Veloce."),
    NewMotorcycle("MV Agusta","Enduro Veloce",931,2024,"Adventure","Παραγωγής","Μεγάλη adventure με τρικύλινδρο κινητήρα."),
    NewMotorcycle("QJMotor","SRT 700",698,2022,"Adventure","Παραγωγής","Δικύλινδρη adventure μεσαίου κυβισμού."),
    NewMotorcycle("QJMotor","SRK 700",698,2022,"Naked","Παραγωγής","Μεσαία naked με δικύλινδρο κινητήρα."),
    NewMotorcycle("QJMotor","SRT 800 SX",799,2023,"Adventure","Παραγωγής","Μεγάλη adventure με δικύλινδρο κινητήρα."),
    NewMotorcycle("QJMotor","SRK 800 RR",778,2024,"Sport","Παραγωγής","Τετρακύλινδρη sport μοτοσυκλέτα."),
    NewMotorcycle("Royal Enfield","Interceptor 650",648,2018,"Roadster","Παραγωγής","Δικύλινδρη retro roadster."),
    NewMotorcycle("Royal Enfield","Continental GT 650",648,2018,"Cafe Racer","Παραγωγής","Cafe racer με δικύλινδρο κινητήρα."),
    NewMotorcycle("Royal Enfield","Super Meteor 650",648,2023,"Cruiser","Παραγωγής","Μεγάλο cruiser της οικογένειας 650."),
    NewMotorcycle("Royal Enfield","Shotgun 650",648,2024,"Custom","Παραγωγής","Custom roadster με βάση την πλατφόρμα 650."),
    NewMotorcycle("Royal Enfield","Himalayan 450",452,2024,"Adventure","Παραγωγής","Νέα υγρόψυκτη μονοκύλινδρη adventure."),
    NewMotorcycle("Royal Enfield","Guerrilla 450",452,2024,"Roadster","Παραγωγής","Roadster με τον κινητήρα Sherpa 450."),
    NewMotorcycle("Royal Enfield","Bear 650",648,2025,"Scrambler","Παραγωγής","Scrambler με δικύλινδρο κινητήρα 650."),
    NewMotorcycle("Royal Enfield","Classic 650",648,2025,"Classic","Παραγωγής","Μεγάλη classic με δικύλινδρο κινητήρα."),
    NewMotorcycle("Suzuki","GSX-S750",749,2017,"Naked","Παραγωγής","Τετρακύλινδρη naked μεσαίου κυβισμού."),
    NewMotorcycle("Suzuki","V-Strom 1000XT",1037,2017,"Adventure","Παραγωγής","Ακτινωτοί τροχοί και αναβαθμίσεις στη μεγάλη V-Strom."),
    NewMotorcycle("Suzuki","Katana",999,2019,"Sport Heritage","Παραγωγής","Επιστροφή του ονόματος Katana με βάση GSX-S1000."),
    NewMotorcycle("Suzuki","V-Strom 1050",1037,2020,"Adventure","Παραγωγής","Νέα γενιά μεγάλης V-Strom."),
    NewMotorcycle("Suzuki","Hayabusa (3rd Gen)",1340,2021,"Hyper Sport","Παραγωγής","Τρίτη γενιά Hayabusa με ηλεκτρονικά βοηθήματα."),
    NewMotorcycle("Suzuki","GSX-8S",776,2023,"Naked","Παραγωγής","Νέα δικύλινδρη πλατφόρμα 776 cc."),
    NewMotorcycle("Suzuki","V-Strom 800DE",776,2023,"Adventure","Παραγωγής","Μεσαία adventure με τροχό 21 ιντσών."),
    NewMotorcycle("Suzuki","GSX-8R",776,2024,"Sport","Παραγωγής","Sport έκδοση της πλατφόρμας GSX-8."),
    NewMotorcycle("Suzuki","DR-Z4S",398,2025,"Dual Sport","Παραγωγής","Νέα γενιά dual-sport μονοκύλινδρης 398 cc."),
    NewMotorcycle("Suzuki","DR-Z4SM",398,2025,"Supermoto","Παραγωγής","Supermoto έκδοση της νέας DR-Z4."),
    NewMotorcycle("Triumph","Street Triple 765",765,2017,"Naked","Παραγωγής","Νέα γενιά τρικύλινδρης Street Triple."),
    NewMotorcycle("Triumph","Speed Twin 1200",1200,2019,"Modern Classic","Παραγωγής","Νέα roadster classic με δικύλινδρο κινητήρα."),
    NewMotorcycle("Triumph","Scrambler 1200",1200,2019,"Scrambler","Παραγωγής","Μεγάλη scrambler με χωμάτινες δυνατότητες."),
    NewMotorcycle("Triumph","Tiger 900",888,2020,"Adventure Touring","Παραγωγής","Νέα γενιά μεσαίας adventure με τρικύλινδρο κινητήρα."),
    NewMotorcycle("Triumph","Trident 660",660,2021,"Naked","Παραγωγής","Τρικύλινδρη naked μεσαίας κατηγορίας."),
    NewMotorcycle("Triumph","Speed Triple 1200 RS",1160,2021,"Naked","Παραγωγής","Νέα γενιά μεγάλης τρικύλινδρης naked."),
    NewMotorcycle("Triumph","Tiger 1200",1160,2022,"Adventure Touring","Παραγωγής","Νέα γενιά μεγάλης adventure touring."),
    NewMotorcycle("Triumph","Tiger Sport 660",660,2022,"Sport Touring","Παραγωγής","Μεσαία sport touring με τρικύλινδρο κινητήρα."),
    NewMotorcycle("Triumph","Speed 400",398,2023,"Roadster","Παραγωγής","Μονοκύλινδρη roadster 398 cc."),
    NewMotorcycle("Triumph","Scrambler 400 X",398,2023,"Scrambler","Παραγωγής","Scrambler έκδοση της πλατφόρμας 400."),
    NewMotorcycle("Triumph","Daytona 660",660,2024,"Sport","Παραγωγής","Sport μοντέλο με τρικύλινδρο κινητήρα."),
    NewMotorcycle("Triumph","TF 450-RC",450,2025,"Motocross","Παραγωγής","Motocross 450 cc υψηλών επιδόσεων."),
    NewMotorcycle("Voge","DS900X",895,2024,"Adventure","Παραγωγής","Μεγάλη adventure με δικύλινδρο κινητήρα."),
    NewMotorcycle("Voge","625DSX",581,2025,"Adventure","Παραγωγής","Μεσαία adventure με δικύλινδρο κινητήρα."),
    NewMotorcycle("Voge","RR660S",660,2025,"Sport","Παρουσιάστηκε","Τετρακύλινδρη sport κατηγορίας 660."),
    NewMotorcycle("Yamaha","XSR700",689,2016,"Sport Heritage","Παραγωγής","Retro roadster με δικύλινδρο CP2."),
    NewMotorcycle("Yamaha","XSR900",847,2016,"Sport Heritage","Παραγωγής","Retro roadster με τρικύλινδρο CP3."),
    NewMotorcycle("Yamaha","MT-10",998,2016,"Hyper Naked","Παραγωγής","Μεγάλη naked με κινητήρα συγγενικό με R1."),
    NewMotorcycle("Yamaha","Ténéré 700",689,2019,"Adventure","Παραγωγής","Μεσαία adventure με κινητήρα CP2."),
    NewMotorcycle("Yamaha","Tracer 9",890,2021,"Sport Touring","Παραγωγής","Νέα γενιά sport touring με CP3."),
    NewMotorcycle("Yamaha","R7",689,2022,"Sport","Παραγωγής","Sport μοντέλο με κινητήρα CP2."),
    NewMotorcycle("Yamaha","XSR900 GP",890,2024,"Sport Heritage","Παραγωγής","Retro sport με βάση την XSR900."),
    NewMotorcycle("Yamaha","MT-09 Y-AMT",890,2024,"Naked","Παραγωγής","Έκδοση MT-09 με αυτοματοποιημένο κιβώτιο."),
    NewMotorcycle("Yamaha","R9",890,2025,"Supersport","Παραγωγής","Νέα supersport με τρικύλινδρο CP3."),
    NewMotorcycle("Zontes","703F",699,2025,"Adventure","Παραγωγής","Τρικύλινδρη adventure της νέας πλατφόρμας 703."),
    NewMotorcycle("Zontes","703RR",699,2025,"Sport","Παραγωγής","Τρικύλινδρη sport της πλατφόρμας 703."),
    // Επίσημα νέα μοντέλα και ουσιαστικές ανανεώσεις που ανακοινώθηκαν έως 23/11/2026.
    NewMotorcycle("Yamaha","RW700",689,2027,"Adventure Scooter","Επίσημη παγκόσμια παρουσίαση EICMA 2026","CP2 689 cc, Y-AMT, YRC και gravel mode. Η Yamaha αναφέρει έναρξη Ιαπωνίας από φθινόπωρο 2027· χρονοδιάγραμμα άλλων αγορών δεν έχει δημοσιευθεί. Πηγή: https://global.yamaha-motor.com/news/2026/1104/eicmarw.html"),
    NewMotorcycle("Yamaha","RW350",327,2027,"Adventure Scooter","Επίσημη παγκόσμια παρουσίαση EICMA 2026","327 cc υγρόψυκτος μονοκύλινδρος, CVT, ηλεκτρονικό γκάζι, traction control και cruise control. Συγκεκριμένη ημερομηνία διάθεσης ανά χώρα δεν έχει ανακοινωθεί. Πηγή: https://global.yamaha-motor.com/news/2026/1104/eicmarw.html"),
    NewMotorcycle("Suzuki","GSX-10R",999,2027,"Sport","Παγκόσμια πρεμιέρα EICMA 2026","999 cc υγρόψυκτος DOHC τετρακύλινδρος. 214 kg, μεταξόνιο 1.455 mm, ρεζερβουάρ 18 L, κατανάλωση WMTC 15,8 km/L και CO₂ 146 g/km. Ισχύς/ροπή δεν δημοσιεύονται στο δελτίο. Διαδοχική διάθεση από καλοκαίρι 2027 ανά χώρα. Πηγή: https://www.suzuki.co.jp/release/b/2026/1103/"),
    NewMotorcycle("Suzuki","SV-7S",645,2027,"Naked","Παγκόσμια πρεμιέρα EICMA 2026","645 cc υγρόψυκτος DOHC V-twin, ride-by-wire, 3 riding modes, 3 επίπεδα traction control, bidirectional quickshifter και TFT 4,2 ιντσών. 200 kg, μεταξόνιο 1.445 mm, WMTC 24,4 km/L και CO₂ 96 g/km. Ισχύς/ροπή δεν δημοσιεύονται στο δελτίο. Διάθεση από άνοιξη 2027 ανά χώρα. Πηγή: https://www.suzuki.co.jp/release/b/2026/1103/"),
    NewMotorcycle("BMW","F 450 R",420,2027,"Naked","Επίσημη παρουσίαση EICMA 2026","420 cc δικύλινδρος εν σειρά 135°, 48 hp, βάρος έτοιμη για οδήγηση 175 kg, Easy Ride Clutch και ABS Pro. Πρεμιέρα EICMA· ημερομηνία εμπορικής διάθεσης ανά αγορά δεν ανακοινώθηκε. Πηγή: https://newsroom.bmwgroup.com/global/eicma-2026/"),
    NewMotorcycle("BMW","F 450 RR",420,2027,"Sport","Επίσημη παρουσίαση EICMA 2026","420 cc δικύλινδρος εν σειρά 135°, 48 hp, 43 Nm, βάρος έτοιμη για οδήγηση 177 kg, σπορ γεωμετρία και ηλεκτρονικά βοηθήματα πίστας. Η ημερομηνία διάθεσης ανά αγορά δεν δημοσιεύθηκε. Πηγή: https://newsroom.bmwgroup.com/global/eicma-2026/"),
    NewMotorcycle("Royal Enfield","Himalayan 750",743,2027,"Adventure","Παρουσιάστηκε και άνοιξαν κρατήσεις στην Ευρώπη","743 cc παράλληλος δικύλινδρος, 63 Nm/5.000 rpm, 19/17 ιντσών tubeless τροχοί, 17 L ρεζερβουάρ, IMU, cornering ABS, traction control και cruise control. Ευρωπαϊκές κρατήσεις από 3/11/2026· τιμές ανακοινώθηκαν για Ιταλία και Ηνωμένο Βασίλειο. Πηγή: https://nsearchives.nseindia.com/corporate/rahulrathore_royalenfield_com_03112026133859_Press_Release_03-11-2026_EICMA_750_Signed.pdf"),
    NewMotorcycle("Royal Enfield","Continental GT 750",743,2027,"Cafe Racer","Επίσημη αποκάλυψη EICMA 2026","743 cc νέα δικύλινδρη πλατφόρμα, cafe racer με faired και unfaired προσεγγίσεις και TFT. Ο κατασκευαστής δεν έχει δημοσιεύσει ακόμη ισχύ, ροπή ή ημερομηνία διάθεσης ανά χώρα. Πηγή: https://nsearchives.nseindia.com/corporate/rahulrathore_royalenfield_com_03112026133859_Press_Release_03-11-2026_EICMA_750_Signed.pdf"),
    NewMotorcycle("Honda","Africa Twin (MY2027 1.131)",1131,2027,"Adventure","Παγκόσμια πρεμιέρα EICMA 2026","Νέα γενιά 1.131 cc υγρόψυκτου OHC δικύλινδρου, 87,5 kW στις 7.500 rpm και 119 Nm στις 5.700 rpm. Εκδόσεις Standard, Adventure Sports και Enduro Sports· σε επιλεγμένη έκδοση νέο radar-assisted Honda SENSING RIDE. Ευρωπαϊκό MY2027, ημερομηνίες ανά εθνική αγορά μη επιβεβαιωμένες. Πηγή: https://global.honda/content/dam/site/global-en/newsroom-new/cq_img/news/2026/11/2261103ceng/2261103ceng.pdf"),
    NewMotorcycle("Honda","CBR1000RR-R Fireblade (MY2027)",999,2027,"Superbike","Παγκόσμια πρεμιέρα EICMA 2026","MY2027 ουσιαστική νέα έκδοση με περίπου 75% ανασχεδιασμένα εξαρτήματα, νέο πλαίσιο/ψαλίδι και διβάθμιες εισαγωγές μεταβλητού μήκους. Η ανακοίνωση δεν δίνει νέα επίσημη ισχύ ή ροπή· δεν μεταφέρονται αριθμοί παλαιότερου MY. Ευρωπαϊκή παρουσίαση για MY2027, χρόνος διάθεσης ανά χώρα μη δημοσιευμένος. Πηγή: https://global.honda/content/dam/site/global-en/newsroom-new/cq_img/news/2026/11/2261103deng/2261103deng.pdf"),
    NewMotorcycle("Triumph","Tiger 1200 (MY2027 update)",1160,2027,"Adventure Touring","Επίσημη ανανέωση 27/10/2026","Ουσιαστική ανανέωση της υπάρχουσας πλατφόρμας 1.160 cc: αναθεωρημένος κινητήρας/κιβώτιο, πλαίσιο, ραντάρ εμπρός-πίσω, Adaptive Cruise και νέα οθόνη 7 ιντσών. Στις εκδόσεις με HeightFlex η σέλα χαμηλώνει αυτόματα έως 30 mm σε στάση. Διαθεσιμότητα εξαρτάται από την εθνική αγορά. Πηγή: https://www.triumphmotorcycles.com/for-the-ride/news/motorcycles/triumph-launches-new-tiger-1200-2026-10-27"),
    NewMotorcycle("Triumph","Street Triple 798 RS",798,2027,"Naked","Επίσημη αποκάλυψη EICMA 2026","Νέα έκδοση με νέο τρικύλινδρο 798 cc και ισχύ 133 hp στην επίσημη σελίδα προϊόντος. Παραγγελίες μέσω δικτύου από την παρουσίαση, πρώτες παραδόσεις αναφέρονται για Φεβρουάριο 2027· διαθεσιμότητα ανά χώρα επιβεβαιώνεται από αντιπρόσωπο. Πηγή: https://www.triumphmotorcycles.com/motorcycles/roadsters/street-triple/street-triple-798-rs"),
    NewMotorcycle("KOVE","350 Rally",350,2027,"Rally","Επίσημη παρουσίαση EICMA 2026","Κινητήρας 349,9 cc (η εγγραφή στρογγυλοποιεί σε 350), ρεζερβουάρ 21 L, τροχοί 21/18 ιντσών και αναρτήσεις μεγάλης διαδρομής. Η KOVE τοποθετεί τα νέα θερμικά μοντέλα στο πλάνο H2 2027· αγορά/ημερομηνία λανσαρίσματος ανά χώρα δεν επιβεβαιώνεται. ",ccExact = "349.9")ηγή: https://www.kovemoto.com/kove-unveils-five-new-models-and-2027-racing-lineups-at-eicma"),
    NewMotorcycle("KOVE","650RR",645,2027,"Sport","Επίσημη παρουσίαση EICMA 2026","645 cc εν σειρά τετρακύλινδρος, 80 kW/13.000 rpm και 60 Nm/11.000 rpm, ride-by-wire, 3 riding modes, quickshifter και cornering ABS. Η KOVE αναφέρει προγραμματισμό για H2 2027· επιβεβαιωμένη ημερομηνία διάθεσης ανά χώρα δεν δημοσιεύθηκε. Πηγή: https://www.kovemoto.com/kove-unveils-five-new-models-and-2027-racing-lineups-at-eicma"),
    NewMotorcycle("Benelli","Tornado Naked Twin 700",698,2027,"Naked","Επίσημη αποκάλυψη EICMA 2026","698 cc παράλληλος δικύλινδρος, quickshifter δύο κατευθύνσεων, cruise control και αναρτήσεις Marzocchi. Η επίσημη ανακοίνωση δεν ορίζει ημερομηνία παραγωγής/διάθεσης ανά αγορά. Πηγή: https://www.benelli.com/int-en/news/benelli-unveils-tornado-naked-twin-700-tornado-naked-tre-903-concept-and-the-production-trk-602-x-at-eicma-2026"),
    NewMotorcycle("Benelli","TRK 602 X",554,2027,"Adventure Touring","Επίσημο μοντέλο παραγωγής, αναμενόμενο στην αγορά","554 cc δικύλινδρος. Η Benelli αναφέρει άφιξη στο δίκτυο αντιπροσώπων έως την άνοιξη 2027· επιβεβαιωμένη ημερομηνία ανά χώρα δεν έχει δοθεί. Πηγή: https://www.benelli.com/int-en/news/benelli-unveils-tornado-naked-twin-700-tornado-naked-tre-903-concept-and-the-production-trk-602-x-at-eicma-2026"),
    NewMotorcycle("Benda","Vinster 700",692,2027,"Roadster","Επίσημη παρουσίαση EICMA 2026","Κινητήρας 691,66 cc υγρόψυκτος V-twin 65°, 54 kW/9.000 rpm, 68 Nm/6.500 rpm, 6 σχέσεις και slipper clutch. Ημερομηνία διάθεσης ανά αγορά δεν έχει ανακοινωθεί. ",ccExact = "691.66")ηγή: https://bendamoto.com/blog/2026/11/05/benda-vinster-700-eicma-2611/"),
    NewMotorcycle("Benda","Vinster X 700",692,2027,"Crossover","Επίσημη παρουσίαση EICMA 2026","Κοινή βάση 691,66 cc V-twin με Vinster 700, crossover προσανατολισμός και εμπρός τροχός 19 ιντσών. Ημερομηνία διάθεσης ανά αγορά δεν έχει ανακοινωθεί. ",ccExact = "691.66")ηγή: https://bendamoto.com/blog/2026/11/05/benda-vinster-700-eicma-2611/"),
    NewMotorcycle("Benda","Capra 700",692,2027,"Adventure","Επίσημη παρουσίαση EICMA 2026","691,66 cc υγρόψυκτος V-twin 65°, 54 kW, 68 Nm, 6 σχέσεις, τροχοί 21/18 ιντσών, ρεζερβουάρ 18 L και βάρος 220 kg. Ημερομηνία διάθεσης ανά αγορά δεν ανακοινώθηκε. ",ccExact = "691.66")ηγή: https://bendamoto.com/blog/2026/11/06/benda-capra-700-eicma-2611-copy/"),
    NewMotorcycle("Fantic","Imola 500",463,2027,"Sport","Παρουσιάστηκε σε τελική διαμόρφωση παραγωγής EICMA 2026","463 cc υγρόψυκτος μονοκύλινδρος DOHC 4 βαλβίδων, ride-by-wire, συμπλέκτης slipper και κιβώτιο 6 σχέσεων. Η Fantic δεν έχει δώσει ημερομηνία λιανικής διάθεσης ανά χώρα. Πηγή: https://www.fantic.com/it-it/content-News/fantic-ad-eicma-2026-tutte-le-novit-_fy41e"),
    NewMotorcycle("Piaggio","Beverly 310 (MY2027 facelift)",310,2027,"Maxi Scooter","Επίσημη ανανέωση μοντέλου 2027","310 hpe, 27,7 hp, νέος LED φωτισμός/τροχοί και TFT 6,88 ιντσών με σύνδεση smartphone. Πρόκειται για ουσιαστικό facelift του Beverly, όχι νέα πλατφόρμα. Η διάθεση και οι τιμές διαφέρουν ανά αγορά. Πηγή: https://press.piaggiogroup.com/en_EN/post/show/361939/the-new-piaggio-beverly-an-ico.html"),
    NewMotorcycle("Piaggio","Beverly 400 (MY2027 facelift)",399,2027,"Maxi Scooter","Επίσημη ανανέωση μοντέλου 2027","399 cc 400 hpe· η επίσημη κυκλοφορία 3/11/2026 αναφέρει 36 hp. Η τεχνική κάρτα Piaggio για το Beverly 400 HPE αναφέρει 399 cc, 26,4 kW και 37,7 Nm· το δελτίο MY2027 δεν δημοσιεύει ξεχωριστή νέα ροπή. Νέα LED, ελαφρύτερες ζάντες, TFT 6,88 ιντσών και βάση top box. Διαθεσιμότητα ανά αγορά να επιβεβαιωθεί. Πηγές: https://press.piaggiogroup.com/en_EN/post/show/361939/the-new-piaggio-beverly-an-ico.html | https://wlassets.piaggio.com/wlassets/piaggio/it/schede-tecniche/Beverly/2025/Piaggio_Beverly_400/original/Piaggio_Beverly_400.pdf?1739534328872="),
    NewMotorcycle("ZXMOTO","820RR",819,2027,"Sport","Επίσημη παρουσίαση EICMA 2026","Τρικύλινδρος 818,8 cc (στρογγυλοποίηση σε 819), 135 hp, 80 Nm και 193 kg βάρος λειτουργίας. Η επίσημη παρουσίαση EICMA αναφέρει ευρωπαϊκή άφιξη στις αρχές 2027. ",ccExact = "818.8")ηγή: https://www.eicma.it/en/zxmoto-at-eicma-with-an-extensive-model-range-for-the-european-market/")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MotoTrackerApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotoTrackerApp() {
    var search by remember { mutableStateOf("") }
    var newSearch by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<Motorcycle?>(null) }
    var tab by remember { mutableStateOf(0) }
    val filteredNew = newMotorcycles.filter { "${it.make} ${it.model} ${it.category} ${it.year}".contains(newSearch, ignoreCase = true) }
    val filtered = motorcycles.filter { "${it.make} ${it.model}".contains(search, ignoreCase = true) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Color(0xFFFF6D00),
            secondary = Color(0xFFFFAB40),
            background = Color(0xFF101114),
            surface = Color(0xFF1B1D22)
        )
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("MOTO TRACKER 300+", fontWeight = FontWeight.Black, letterSpacing = 1.sp)
                            Text("Ο οδηγός μοτοσυκλέτας", fontSize = 12.sp, color = MaterialTheme.colorScheme.primary)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF17191D), titleContentColor = Color.White)
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF17191D)) {
                    NavigationBarItem(selected = tab == 0, onClick = { tab = 0; selected = null }, icon = { Text("🏍️") }, label = { Text("Μοντέλα") })
                    NavigationBarItem(selected = tab == 1, onClick = { tab = 1; selected = null }, icon = { Text("⚖️") }, label = { Text("Σύγκριση") })
                    NavigationBarItem(selected = tab == 2, onClick = { tab = 2; selected = null }, icon = { Text("ℹ️") }, label = { Text("Οδηγός") })
                    NavigationBarItem(selected = tab == 3, onClick = { tab = 3; selected = null }, icon = { Text("🆕") }, label = { Text("Νέα 300+") })
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { padding ->
            when (tab) {
                0 -> {
                    if (selected == null) {
                        Column(Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
                            Text("Βρες τη μοτοσυκλέτα σου", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(12.dp))
                            OutlinedTextField(value = search, onValueChange = { search = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Αναζήτηση μάρκας ή μοντέλου") }, leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }, singleLine = true)
                            Spacer(Modifier.height(12.dp))
                            Text("${filtered.size} μοντέλα στη βάση", color = Color.LightGray, fontSize = 13.sp)
                            Spacer(Modifier.height(8.dp))
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                items(filtered) { bike ->
                                    Card(onClick = { selected = bike }, modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF202329)), shape = RoundedCornerShape(16.dp)) {
                                        Column(Modifier.padding(16.dp)) {
                                            Text(bike.make.uppercase(), color = MaterialTheme.colorScheme.primary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                            Text(bike.model, color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                                            Text(bike.years, color = Color.LightGray)
                                            Spacer(Modifier.height(8.dp))
                                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                                Stat("Ισχύς", bike.power)
                                                Stat("Κινητήρας", bike.engine)
                                                Stat("Κατανάλωση", bike.consumption)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Column(Modifier.padding(padding).fillMaxSize().padding(20.dp)) {
                            TextButton(onClick = { selected = null }) { Text("← Πίσω στα μοντέλα") }
                            Text(selected!!.make, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                            Text(selected!!.model, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Text(selected!!.years, color = Color.LightGray)
                            Spacer(Modifier.height(20.dp))
                            Stat("Ισχύς", selected!!.power)
                            Stat("Κινητήρας", selected!!.engine)
                            Stat("Ενδεικτική κατανάλωση", selected!!.consumption)
                            Spacer(Modifier.height(20.dp))
                            Text("Τι να προσέξεις", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text(selected!!.notes, fontSize = 16.sp, lineHeight = 24.sp)
                            Spacer(Modifier.height(16.dp))
                            Text("Οι τιμές είναι ενδεικτικές και διαφέρουν ανά έκδοση, χρήση και κατάσταση οχήματος.", color = Color.LightGray, fontSize = 12.sp)
                        }
                    }
                }
                1 -> Column(Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
                    Text("Σύγκριση μοντέλων", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("Γρήγορη σύγκριση βασικών χαρακτηριστικών", color = Color.LightGray)
                    motorcycles.forEach { bike ->
                        Card(Modifier.fillMaxWidth().padding(vertical = 5.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF202329))) {
                            Row(Modifier.padding(12.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Column(Modifier.weight(1f)) {
                                    Text("${bike.make} ${bike.model}", fontWeight = FontWeight.Bold)
                                    Text(bike.engine, color = Color.LightGray, fontSize = 12.sp)
                                }
                                Text(bike.power, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
                2 -> Column(Modifier.padding(padding).fillMaxSize().padding(20.dp)) {
                    Text("Οδηγός αγοράς", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    GuideItem("1. Ιστορικό συντήρησης", "Ζήτησε βιβλίο service, τιμολόγια και έλεγχο χιλιομέτρων.")
                    GuideItem("2. Κινητήρας και μετάδοση", "Έλεγξε κρύα εκκίνηση, θορύβους, διαρροές, συμπλέκτη και κιβώτιο.")
                    GuideItem("3. Αναρτήσεις και φρένα", "Έλεγξε τσιμούχες, δίσκους, τακάκια, ρουλεμάν και ελαστικά.")
                    GuideItem("4. Ηλεκτρικά και ηλεκτρονικά", "Δοκίμασε ABS, traction control, οθόνη, φόρτιση και όλα τα χειριστήρια.")
                    GuideItem("5. Δοκιμή και ανεξάρτητος έλεγχος", "Κάνε δοκιμή δρόμου και προαγοραστικό έλεγχο σε εξειδικευμένο συνεργείο.")
                }
                else -> Column(Modifier.padding(padding).fillMaxSize().padding(16.dp)) {
                    Text("ΝΕΑ ΜΟΝΤΕΛΑ 300+ cc", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Παγκόσμια νέα μοντέλα 2016–2026", color = Color.LightGray, fontSize = 13.sp)
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(value = newSearch, onValueChange = { newSearch = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Μάρκα, μοντέλο, έτος ή κατηγορία") }, leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }, singleLine = true)
                    Spacer(Modifier.height(8.dp))
                    Text("${filteredNew.size} καταχωρήσεις • άνω των 300 cc", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Spacer(Modifier.height(8.dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                        items(filteredNew) { bike ->
                            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF202329)), shape = RoundedCornerShape(14.dp)) {
                                Column(Modifier.padding(14.dp)) {
                                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(bike.make.uppercase(), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        Text("${bike.ccExact} cc", color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                    Text(bike.model, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                    Text("${bike.year} • ${bike.category}", color = Color.LightGray, fontSize = 12.sp)
                                    Spacer(Modifier.height(5.dp))
                                    Text(bike.status, color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                    Text(bike.details, color = Color.White, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text("Ενημερωτικός κατάλογος νέων μοντέλων/πλατφορμών. Το έτος μπορεί να διαφέρει ανά αγορά. Πριν από αγορά, επιβεβαίωσε προδιαγραφές και διαθεσιμότητα από τον επίσημο εισαγωγέα.", color = Color.LightGray, fontSize = 11.sp)
                }
            }
        }
    }
}

@Composable
private fun Stat(label: String, value: String) {
    Column(Modifier.padding(end = 8.dp)) {
        Text(label, color = Color.LightGray, fontSize = 11.sp)
        Text(value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

@Composable
private fun GuideItem(title: String, body: String) {
    Card(Modifier.fillMaxWidth().padding(vertical = 6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF202329))) {
        Column(Modifier.padding(14.dp)) {
            Text(title, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(5.dp))
            Text(body, color = Color.White)
        }
    }
}
