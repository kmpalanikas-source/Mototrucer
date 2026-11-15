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
    var selected by remember { mutableStateOf<Motorcycle?>(null) }
    var tab by remember { mutableStateOf(0) }
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
                else -> Column(Modifier.padding(padding).fillMaxSize().padding(20.dp)) {
                    Text("Οδηγός αγοράς", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    GuideItem("1. Ιστορικό συντήρησης", "Ζήτησε βιβλίο service, τιμολόγια και έλεγχο χιλιομέτρων.")
                    GuideItem("2. Κινητήρας και μετάδοση", "Έλεγξε κρύα εκκίνηση, θορύβους, διαρροές, συμπλέκτη και κιβώτιο.")
                    GuideItem("3. Αναρτήσεις και φρένα", "Έλεγξε τσιμούχες, δίσκους, τακάκια, ρουλεμάν και ελαστικά.")
                    GuideItem("4. Ηλεκτρικά και ηλεκτρονικά", "Δοκίμασε ABS, traction control, οθόνη, φόρτιση και όλα τα χειριστήρια.")
                    GuideItem("5. Δοκιμή και ανεξάρτητος έλεγχος", "Κάνε δοκιμή δρόμου και προαγοραστικό έλεγχο σε εξειδικευμένο συνεργείο.")
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
