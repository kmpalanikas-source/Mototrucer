package gr.mototracker300plus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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


data class NewMotorcycle(val make: String, val model: String, val cc: Int, val category: String, val status: String, val details: String)
private val newMotorcycles = listOf(
 NewMotorcycle("BMW","F 450 R",420,"Naked","Νέο μοντέλο 2027","Δικύλινδρος 420 cc, 48 hp, Easy Ride Clutch."),
 NewMotorcycle("BMW","F 450 RR",420,"Sport","Νέο μοντέλο 2027","Δικύλινδρος 420 cc, 48 hp, supersport σχεδίαση."),
 NewMotorcycle("BMW","M 1000 RS",999,"Hyper Naked","Παγκόσμια πρεμιέρα 2027","218 hp και 196 kg."),
 NewMotorcycle("CFMoto","550MT",526,"Adventure","Νέα παρουσίαση 2027","Adventure μεσαίου κυβισμού."),
 NewMotorcycle("CFMoto","550MT-X",526,"Adventure","Νέα παρουσίαση 2027","Έκδοση X με χωμάτινο προσανατολισμό."),
 NewMotorcycle("CFMoto","V4SR-RR",1000,"Superbike","Νέα παρουσίαση 2027","Νέα superbike με τετρακύλινδρο V4."),
 NewMotorcycle("Aprilia","RS 660 Phillip Island Tribute",659,"Sport","Ειδική έκδοση 2027","Ειδική έκδοση της RS 660 με 105 hp."),
 NewMotorcycle("Aprilia","RSV4 Obscure Spe.Ed.",1099,"Superbike","Ειδική έκδοση 2027","Περιορισμένη έκδοση με carbon τροχούς και αεροδυναμικά στοιχεία."),
 NewMotorcycle("Harley-Davidson","Sprint 440",440,"Roadster","Νέα παρουσίαση 2027","Νέο μοντέλο εισόδου στη γκάμα Harley-Davidson."),
 NewMotorcycle("KTM","450 RC",449,"Sport","Παρουσίαση για το 2028","Νέα sport μοτοσυκλέτα μεσαίου κυβισμού."),
 NewMotorcycle("Ducati","Panigale V4 SP",1103,"Superbike","Ειδική έκδοση 2027","Έκδοση SP περιορισμένης παραγωγής."),
 NewMotorcycle("Ducati","Streetfighter V4 SP",1103,"Naked","Ειδική έκδοση 2027","Έκδοση SP περιορισμένης παραγωγής."),
 NewMotorcycle("Ducati","Panigale V2 SP",890,"Sport","Ειδική έκδοση 2027","Ειδική έκδοση της νέας Panigale V2."),
 NewMotorcycle("Ducati","DesertX Rally",937,"Adventure","Νέα έκδοση 2027","Rally προσανατολισμός και αναβαθμισμένος εξοπλισμός."),
 NewMotorcycle("Ducati","Diavel V4 / V4 S",1158,"Cruiser","Νέες εκδόσεις 2027","V4 cruiser με ανανεωμένη γκάμα."),
 NewMotorcycle("Honda","CRF1150L Africa Twin",1100,"Adventure","Νέα γενιά 2027","Νέα γενιά Africa Twin με αναβαθμίσεις ασφάλειας."),
 NewMotorcycle("Honda","CBR1000RR-R Fireblade",1000,"Superbike","Νέα γενιά 2027","Νέα γενιά Fireblade για το 2027."),
 NewMotorcycle("Honda","V3R 900 E-Compressor",900,"Sport","Προγραμματισμένο μοντέλο παραγωγής","V3 κινητήρας με ηλεκτρονικά ελεγχόμενο συμπιεστή."),
 NewMotorcycle("Suzuki","GSX-10R",999,"Superbike","Νέα παρουσίαση 2027","Νέα superbike της σειράς GSX."),
 NewMotorcycle("Suzuki","SV-7S",645,"Naked","Νέα παρουσίαση 2027","Μεσαίου κυβισμού δικύλινδρη πρόταση."),
 NewMotorcycle("Benda","Capra 700",700,"Cruiser","Νέα παρουσίαση 2027","Νέο cruiser της Benda."),
 NewMotorcycle("Benda","Vinster 700",700,"Cruiser","Νέα παρουσίαση 2027","Νέα cruiser πρόταση."),
 NewMotorcycle("Benda","Dark Flag 600 Ultra EC",600,"Cruiser","Νέα παρουσίαση 2027","Cruiser με ηλεκτρονικό συμπλέκτη."),
 NewMotorcycle("KTM","1390 Super Duke GT EVO",1350,"Sport Touring","Νέα παρουσίαση 2027","Νέα έκδοση sport touring της σειράς 1390."),
 NewMotorcycle("QJMotor","Rino 900",900,"Adventure","Νέα παρουσίαση 2027","Νέα adventure πρόταση."),
 NewMotorcycle("QJMotor","SRV 600 VH",600,"Cruiser","Νέα παρουσίαση 2027","Νέο cruiser μεσαίου κυβισμού."),
 NewMotorcycle("Royal Enfield","Himalayan 750",750,"Adventure","Νέα παρουσίαση 2027","Νέα δικύλινδρη adventure πλατφόρμα."),
 NewMotorcycle("Royal Enfield","Shotgun 650 Son of a Gun",648,"Custom","Ειδική έκδοση 2027","Ειδική έκδοση της Shotgun 650."),
 NewMotorcycle("BSA","Bantam Bobber 350",350,"Bobber","Νέα παρουσίαση 2027","Bobber με κινητήρα 350 cc."),
 NewMotorcycle("BSA","Scrambler 350",350,"Scrambler","Νέα παρουσίαση 2027","Scrambler με κινητήρα 350 cc."),
 NewMotorcycle("Moto Guzzi","V85 TT Napapijri",853,"Adventure","Ειδική έκδοση 2027","Ειδική έκδοση της V85 TT."),
 NewMotorcycle("Benelli","Tornado Naked Twin 700",700,"Naked","Νέα παρουσίαση 2027","Νέα δικύλινδρη naked."),
 NewMotorcycle("Fantic","Caballero 500 Supermoto",500,"Supermoto","Νέα παρουσίαση 2027","Supermoto έκδοση της Caballero 500."),
 NewMotorcycle("Voge","660RR",660,"Sport","Νέα παρουσίαση 2027","Νέα sport μοτοσυκλέτα."),
 NewMotorcycle("Voge","R800",800,"Naked","Νέα παρουσίαση 2027","Νέα naked πρόταση."),
 NewMotorcycle("Norton","450",450,"Road","Νέα παρουσίαση 2027","Νέο μοντέλο της Norton."),
 NewMotorcycle("Zontes","ZT853-R",853,"Naked","Νέα παρουσίαση 2027","Νέα μεγάλη μοτοσυκλέτα της Zontes."),
 NewMotorcycle("Kove","650RR",645,"Sport","Ευρωπαϊκή έκδοση 2027","Τετρακύλινδρη 645 cc, 109 hp."),
 NewMotorcycle("Kove","350 Rally",349,"Rally","Νέα παρουσίαση 2027","349,9 cc και 21 λίτρα ρεζερβουάρ."),
 NewMotorcycle("Kawasaki","Versys 900",948,"Sport Touring","Νέο μοντέλο 2027","122 hp και συστήματα υποβοήθησης αναβάτη."),
 NewMotorcycle("Kawasaki","KLE500",451,"Adventure","Νέο μοντέλο 2027","Νέα adventure μεσαίου κυβισμού."),
 NewMotorcycle("Triumph","Street Triple 798",798,"Naked","Νέα παρουσίαση 2027","Νέα έκδοση Street Triple."),
 NewMotorcycle("Triumph","Tiger 1200",1160,"Adventure Touring","Νέα παρουσίαση 2027","Ανανέωση της μεγάλης adventure touring.")
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
    val filteredNew = newMotorcycles.filter { "${it.make} ${it.model} ${it.category}".contains(newSearch, ignoreCase = true) }
    val filtered = motorcycles.filter {
        "${it.make} ${it.model}".contains(search, ignoreCase = true)
    }

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
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF17191D),
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF17191D)) {
                    NavigationBarItem(
                        selected = tab == 0,
                        onClick = { tab = 0; selected = null },
                        icon = { Text("🏍️") },
                        label = { Text("Μοντέλα") }
                    )
                    NavigationBarItem(
                        selected = tab == 1,
                        onClick = { tab = 1; selected = null },
                        icon = { Text("⚖️") },
                        label = { Text("Σύγκριση") }
                    )
                    NavigationBarItem(
                        selected = tab == 2,
                        onClick = { tab = 2; selected = null },
                        icon = { Text("ℹ️") },
                        label = { Text("Οδηγός") }
                    )
                    NavigationBarItem(
                        selected = tab == 3,
                        onClick = { tab = 3; selected = null },
                        icon = { Text("🆕") },
                        label = { Text("Νέα 300+") }
                    )
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
                            OutlinedTextField(
                                value = search,
                                onValueChange = { search = it },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text("Αναζήτηση μάρκας ή μοντέλου") },
                                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                                singleLine = true
                            )
                            Spacer(Modifier.height(12.dp))
                            Text("${filtered.size} μοντέλα στη βάση", color = Color.LightGray, fontSize = 13.sp)
                            Spacer(Modifier.height(8.dp))
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                items(filtered) { bike ->
                                    Card(
                                        onClick = { selected = bike },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = CardDefaults.cardColors(containerColor = Color(0xFF202329)),
                                        shape = RoundedCornerShape(16.dp)
                                    ) {
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
                    Text("Παγκόσμιες παρουσιάσεις 2026–2027", color = Color.LightGray, fontSize = 13.sp)
                    Spacer(Modifier.height(10.dp))
                    OutlinedTextField(value = newSearch, onValueChange = { newSearch = it }, modifier = Modifier.fillMaxWidth(), placeholder = { Text("Μάρκα, μοντέλο ή κατηγορία") }, leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }, singleLine = true)
                    Spacer(Modifier.height(8.dp))
                    Text("${filteredNew.size} καταχωρήσεις • άνω των 300 cc", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    Spacer(Modifier.height(8.dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                        items(filteredNew) { bike ->
                            Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = Color(0xFF202329)), shape = RoundedCornerShape(14.dp)) {
                                Column(Modifier.padding(14.dp)) {
                                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                        Text(bike.make.uppercase(), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                        Text("${bike.cc} cc", color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                    Text(bike.model, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                    Text(bike.category, color = Color.LightGray, fontSize = 12.sp)
                                    Spacer(Modifier.height(5.dp))
                                    Text(bike.status, color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                    Text(bike.details, color = Color.White, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(6.dp))
                    Text("Σημείωση: οι καταχωρήσεις βασίζονται σε ανακοινώσεις κατασκευαστών και εκθέσεων. Επιβεβαίωσε τεχνικά στοιχεία, διαθεσιμότητα και τιμές ανά χώρα.", color = Color.LightGray, fontSize = 11.sp)
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
