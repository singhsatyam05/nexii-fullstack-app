package com.swastik.app.data

import com.swastik.app.data.model.*

object SampleData {

    val categories = listOf(
        Category("1", "Smartphones", "📱", 156),
        Category("2", "Laptops", "💻", 89),
        Category("3", "Televisions", "📺", 67),
        Category("4", "Audio", "🎧", 124),
        Category("5", "Cameras", "📷", 45),
        Category("6", "Gaming", "🎮", 78),
        Category("7", "Tablets", "📲", 34),
        Category("8", "Wearables", "⌚", 92),
        Category("9", "Accessories", "🔌", 210),
        Category("10", "Home Appliances", "🏠", 88)
    )

    val shops = listOf(
        Shop("s1", "Digital Galaxy Store", "Rahul Sharma", "MG Road, Sector 14, Gurgaon",
            "9876543210", "digital.galaxy@email.com", "07AABCU9603R1ZM",
            4.5f, 234, 2.3, true,
            "https://images.unsplash.com/photo-1531297484001-80022131f5a1?w=400&h=200&fit=crop",
            listOf("Smartphones", "Laptops", "Tablets"), 245),
        Shop("s2", "TechZone Electronics", "Amit Verma", "Connaught Place, New Delhi",
            "9876543211", "techzone@email.com", "07AABCU9603R1ZN",
            4.2f, 189, 5.1, true,
            "https://images.unsplash.com/photo-1441986300917-64674bd600d8?w=400&h=200&fit=crop",
            listOf("Televisions", "Audio", "Home Appliances"), 178),
        Shop("s3", "Gadget Hub", "Priya Singh", "Noida Sector 18",
            "9876543212", "gadgethub@email.com", "07AABCU9603R1ZO",
            4.7f, 312, 8.5, true,
            "https://images.unsplash.com/photo-1491933382434-500287f9b54b?w=400&h=200&fit=crop",
            listOf("Gaming", "Cameras", "Wearables"), 156),
        Shop("s4", "ElectroMart", "Vikram Patel", "Dwarka Sector 21, New Delhi",
            "9876543213", "electromart@email.com", "07AABCU9603R1ZP",
            4.0f, 145, 12.0, true,
            "https://images.unsplash.com/photo-1550009158-9ebf69173e03?w=400&h=200&fit=crop",
            listOf("Smartphones", "Accessories"), 98),
        Shop("s5", "Smart World", "Neha Gupta", "Rajouri Garden, New Delhi",
            "9876543214", "smartworld@email.com", "07AABCU9603R1ZQ",
            4.6f, 278, 15.2, true,
            "https://images.unsplash.com/photo-1526738549149-8e07eca6c147?w=400&h=200&fit=crop",
            listOf("Laptops", "Tablets", "Wearables"), 189),
        Shop("s6", "Audio Pro", "Rohan Das", "Vasant Kunj, New Delhi",
            "9876543215", "audiopro@email.com", "07AABCU9603R1ZR",
            4.8f, 412, 4.5, true,
            "https://images.unsplash.com/photo-1558089687-f282ffcbc126?w=400&h=200&fit=crop",
            listOf("Audio", "Accessories"), 210),
        Shop("s7", "Mobile Square", "Anita Roy", "Lajpat Nagar, New Delhi",
            "9876543216", "mobilesquare@email.com", "07AABCU9603R1ZS",
            4.3f, 156, 9.2, true,
            "https://images.unsplash.com/photo-1601784551446-20c9e07cd50e?w=400&h=200&fit=crop",
            listOf("Smartphones", "Accessories", "Tablets"), 120),
        Shop("s8", "ComputeTech", "Sanjay Mishra", "Nehru Place, New Delhi",
            "9876543217", "computetech@email.com", "07AABCU9603R1ZT",
            4.6f, 890, 18.5, true,
            "https://images.unsplash.com/photo-1593640408182-31c70c8268f5?w=400&h=200&fit=crop",
            listOf("Laptops", "Gaming", "Accessories"), 450),
        Shop("s9", "Vision Electronics", "Kavita Sharma", "South Extension, New Delhi",
            "9876543218", "vision@email.com", "07AABCU9603R1ZU",
            4.1f, 89, 7.8, true,
            "https://images.unsplash.com/photo-1542831371-29b0f74f9713?w=400&h=200&fit=crop",
            listOf("Televisions", "Home Appliances"), 65),
        Shop("s10", "Wearable Hub", "Deepak Kumar", "DLF Cyber City, Gurgaon",
            "9876543219", "wearables@email.com", "07AABCU9603R1ZV",
            4.9f, 567, 3.1, true,
            "https://images.unsplash.com/photo-1555448248-2571daf6344b?w=400&h=200&fit=crop",
            listOf("Wearables", "Audio", "Smartphones"), 320)
    )

    val products = listOf(
        Product(
            id = "p1", name = "iPhone 15 Pro Max", brand = "Apple",
            category = "Smartphones",
            description = "The most powerful iPhone ever with A17 Pro chip, 48MP camera system, and titanium design.",
            specifications = mapOf(
                "Display" to "6.7\" Super Retina XDR OLED",
                "Processor" to "A17 Pro Bionic",
                "RAM" to "8 GB",
                "Storage" to "256 GB",
                "Camera" to "48MP + 12MP + 12MP",
                "Battery" to "4422 mAh",
                "OS" to "iOS 17"
            ),
            mrp = 159900.0, sellingPrice = 144900.0, discountPercent = 9,
            rating = 4.7f, reviewCount = 1245,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1695048133142-1a20484d2569?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1592750475338-74b7b21085ab?w=400&h=400&fit=crop"
            ),
            shopId = "s1", shopName = "Digital Galaxy Store",
            quantity = 15, warranty = "1 Year Apple Warranty",
            manufacturerDetails = "Apple Inc., Cupertino, California, USA"
        ),
        Product(
            id = "p2", name = "Samsung Galaxy S24 Ultra", brand = "Samsung",
            category = "Smartphones",
            description = "Galaxy AI powered smartphone with S Pen, 200MP camera, and titanium frame.",
            specifications = mapOf(
                "Display" to "6.8\" Dynamic AMOLED 2X",
                "Processor" to "Snapdragon 8 Gen 3",
                "RAM" to "12 GB",
                "Storage" to "256 GB",
                "Camera" to "200MP + 12MP + 50MP + 10MP",
                "Battery" to "5000 mAh",
                "OS" to "Android 14, One UI 6.1"
            ),
            mrp = 129999.0, sellingPrice = 117999.0, discountPercent = 9,
            rating = 4.5f, reviewCount = 987,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1610945265064-0e34e5519bbf?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1585060544812-6b45742d762f?w=400&h=400&fit=crop"
            ),
            shopId = "s1", shopName = "Digital Galaxy Store",
            quantity = 22, warranty = "1 Year Samsung Warranty",
            manufacturerDetails = "Samsung Electronics, South Korea"
        ),
        Product(
            id = "p3", name = "MacBook Air M3", brand = "Apple",
            category = "Laptops",
            description = "Supercharged by M3 chip. 18 hours battery life. Fanless design.",
            specifications = mapOf(
                "Display" to "13.6\" Liquid Retina",
                "Processor" to "Apple M3 Chip",
                "RAM" to "8 GB Unified",
                "Storage" to "256 GB SSD",
                "Battery" to "Up to 18 hours",
                "Weight" to "1.24 kg",
                "Ports" to "2x Thunderbolt, MagSafe, 3.5mm"
            ),
            mrp = 114900.0, sellingPrice = 99900.0, discountPercent = 13,
            rating = 4.8f, reviewCount = 654,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1611186871348-b1ce696e52c9?w=400&h=400&fit=crop"
            ),
            shopId = "s5", shopName = "Smart World",
            quantity = 8, warranty = "1 Year Apple Warranty",
            manufacturerDetails = "Apple Inc., Cupertino, California, USA"
        ),
        Product(
            id = "p4", name = "Sony WH-1000XM5", brand = "Sony",
            category = "Audio",
            description = "Industry-leading noise cancellation with exceptional sound quality.",
            specifications = mapOf(
                "Type" to "Over-Ear Wireless",
                "Driver" to "30mm",
                "ANC" to "Yes, Adaptive",
                "Battery" to "30 hours",
                "Bluetooth" to "5.2",
                "Weight" to "250g",
                "Codec" to "LDAC, AAC, SBC"
            ),
            mrp = 29990.0, sellingPrice = 24990.0, discountPercent = 17,
            rating = 4.6f, reviewCount = 432,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1583394838336-acd977736f90?w=400&h=400&fit=crop"
            ),
            shopId = "s2", shopName = "TechZone Electronics",
            quantity = 30, warranty = "1 Year Sony Warranty",
            manufacturerDetails = "Sony Corporation, Japan"
        ),
        Product(
            id = "p5", name = "Samsung 55\" QLED 4K TV", brand = "Samsung",
            category = "Televisions",
            description = "Quantum Dot technology with 4K resolution and smart TV features.",
            specifications = mapOf(
                "Screen Size" to "55 inches",
                "Resolution" to "4K Ultra HD (3840 x 2160)",
                "Panel" to "QLED",
                "HDR" to "Quantum HDR",
                "Smart TV" to "Tizen OS",
                "Sound" to "20W, Dolby Digital Plus",
                "HDMI Ports" to "3"
            ),
            mrp = 74990.0, sellingPrice = 59990.0, discountPercent = 20,
            rating = 4.4f, reviewCount = 321,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1593359677879-a4bb92f829d1?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1461151304267-38535e780c79?w=400&h=400&fit=crop"
            ),
            shopId = "s2", shopName = "TechZone Electronics",
            quantity = 5, warranty = "2 Years Samsung Warranty",
            manufacturerDetails = "Samsung Electronics, South Korea"
        ),
        Product(
            id = "p6", name = "PlayStation 5", brand = "Sony",
            category = "Gaming",
            description = "Next-gen gaming console with ultra-high speed SSD and ray tracing.",
            specifications = mapOf(
                "CPU" to "AMD Zen 2, 8 Cores",
                "GPU" to "10.28 TFLOPS, AMD RDNA 2",
                "RAM" to "16 GB GDDR6",
                "Storage" to "825 GB SSD",
                "Resolution" to "Up to 8K",
                "Frame Rate" to "Up to 120 fps",
                "Disc Drive" to "4K UHD Blu-ray"
            ),
            mrp = 54990.0, sellingPrice = 49990.0, discountPercent = 9,
            rating = 4.9f, reviewCount = 876,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1621259182978-fbf93132d53d?w=400&h=400&fit=crop"
            ),
            shopId = "s3", shopName = "Gadget Hub",
            quantity = 12, warranty = "1 Year Sony Warranty",
            manufacturerDetails = "Sony Interactive Entertainment, Japan"
        ),
        Product(
            id = "p7", name = "iPad Air M2", brand = "Apple",
            category = "Tablets",
            description = "Powerful M2 chip, Liquid Retina display, and all-day battery life.",
            specifications = mapOf(
                "Display" to "11\" Liquid Retina",
                "Processor" to "Apple M2",
                "RAM" to "8 GB",
                "Storage" to "128 GB",
                "Camera" to "12MP Wide",
                "Battery" to "Up to 10 hours",
                "Connectivity" to "Wi-Fi 6E"
            ),
            mrp = 59900.0, sellingPrice = 54900.0, discountPercent = 8,
            rating = 4.5f, reviewCount = 234,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1561154464-82e9aab73691?w=400&h=400&fit=crop"
            ),
            shopId = "s5", shopName = "Smart World",
            quantity = 18, warranty = "1 Year Apple Warranty",
            manufacturerDetails = "Apple Inc., Cupertino, California, USA"
        ),
        Product(
            id = "p8", name = "Apple Watch Ultra 2", brand = "Apple",
            category = "Wearables",
            description = "The most rugged Apple Watch with precision GPS and 36-hour battery.",
            specifications = mapOf(
                "Display" to "49mm Always-On Retina",
                "Chip" to "S9 SiP",
                "Battery" to "Up to 36 hours",
                "Water Resistance" to "100m / EN13319",
                "GPS" to "Precision Dual-Frequency",
                "Material" to "Titanium Case",
                "Sensors" to "Heart Rate, Blood Oxygen, Temperature"
            ),
            mrp = 89900.0, sellingPrice = 79900.0, discountPercent = 11,
            rating = 4.7f, reviewCount = 189,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1434493789847-2f02dc6ca35d?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1551816230-ef5deaed4a26?w=400&h=400&fit=crop"
            ),
            shopId = "s3", shopName = "Gadget Hub",
            quantity = 25, warranty = "1 Year Apple Warranty",
            manufacturerDetails = "Apple Inc., Cupertino, California, USA"
        ),
        Product(
            id = "p9", name = "Canon EOS R6 Mark II", brand = "Canon",
            category = "Cameras",
            description = "Full-frame mirrorless camera with 24.2MP sensor and 40fps burst.",
            specifications = mapOf(
                "Sensor" to "24.2MP Full-Frame CMOS",
                "Processor" to "DIGIC X",
                "ISO Range" to "100–102400",
                "Video" to "4K 60p, Full HD 180p",
                "AF Points" to "1053",
                "Stabilization" to "In-body 8-stop",
                "Weight" to "670g (body)"
            ),
            mrp = 249995.0, sellingPrice = 224995.0, discountPercent = 10,
            rating = 4.8f, reviewCount = 98,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1502920917128-1aa500764cbd?w=400&h=400&fit=crop"
            ),
            shopId = "s3", shopName = "Gadget Hub",
            quantity = 4, warranty = "2 Years Canon Warranty",
            manufacturerDetails = "Canon Inc., Tokyo, Japan"
        ),
        Product(
            id = "p10", name = "boAt Airdopes 441", brand = "boAt",
            category = "Audio",
            description = "True wireless earbuds with IWP technology and IPX7 water resistance.",
            specifications = mapOf(
                "Type" to "True Wireless Earbuds",
                "Driver" to "6mm",
                "Battery" to "5 hours + 25 hours (case)",
                "Bluetooth" to "5.0",
                "Water Resistance" to "IPX7",
                "Weight" to "4g per earbud",
                "Charging" to "USB Type-C"
            ),
            mrp = 3990.0, sellingPrice = 1299.0, discountPercent = 67,
            rating = 4.1f, reviewCount = 5678,
            imageUrls = listOf(
                "https://images.unsplash.com/photo-1590658268037-6bf12f032f55?w=400&h=400&fit=crop",
                "https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb?w=400&h=400&fit=crop"
            ),
            shopId = "s4", shopName = "ElectroMart",
            quantity = 100, warranty = "1 Year boAt Warranty",
            manufacturerDetails = "Imagine Marketing Ltd., India"
        ),
        Product(
            id = "p11", name = "HP Envy x360", brand = "HP",
            category = "Laptops",
            description = "Versatile 2-in-1 laptop with OLED display and powerful Intel processor.",
            specifications = mapOf("Display" to "15.6\" OLED Touch", "Processor" to "Intel Core i7 13th Gen", "RAM" to "16 GB", "Storage" to "512 GB SSD"),
            mrp = 110000.0, sellingPrice = 95000.0, discountPercent = 13,
            rating = 4.4f, reviewCount = 312,
            imageUrls = listOf("https://images.unsplash.com/photo-1531297484001-80022131f5a1?w=400&h=400&fit=crop"),
            shopId = "s4", shopName = "ElectroMart", quantity = 10, warranty = "1 Year HP Warranty", manufacturerDetails = "HP Inc."
        ),
        Product(
            id = "p12", name = "Dell XPS 15", brand = "Dell",
            category = "Laptops",
            description = "Premium creator laptop with InfinityEdge display.",
            specifications = mapOf("Display" to "15.6\" 4K UHD+", "Processor" to "Intel Core i9", "RAM" to "32 GB", "Storage" to "1 TB SSD"),
            mrp = 250000.0, sellingPrice = 225000.0, discountPercent = 10,
            rating = 4.6f, reviewCount = 145,
            imageUrls = listOf("https://images.unsplash.com/photo-1593642632823-8f785ba67e45?w=400&h=400&fit=crop"),
            shopId = "s1", shopName = "Digital Galaxy Store", quantity = 5, warranty = "1 Year Dell Warranty", manufacturerDetails = "Dell Technologies"
        ),
        Product(
            id = "p13", name = "ASUS ROG Strix G16", brand = "ASUS",
            category = "Gaming",
            description = "High-performance gaming laptop with Nebula Display.",
            specifications = mapOf("Display" to "16\" QHD 240Hz", "Processor" to "Intel Core i7 13650HX", "GPU" to "RTX 4060"),
            mrp = 160000.0, sellingPrice = 145000.0, discountPercent = 9,
            rating = 4.7f, reviewCount = 890,
            imageUrls = listOf("https://images.unsplash.com/photo-1603302576837-37561b2e2302?w=400&h=400&fit=crop"),
            shopId = "s3", shopName = "Gadget Hub", quantity = 15, warranty = "1 Year Asus Warranty", manufacturerDetails = "ASUS"
        ),
        Product(
            id = "p14", name = "NoiseFit Halo Smartwatch", brand = "Noise",
            category = "Wearables",
            description = "AMOLED display smartwatch with bluetooth calling.",
            specifications = mapOf("Display" to "1.43\" AMOLED", "Battery" to "7 Days", "Features" to "BT Calling, Heart Rate"),
            mrp = 7999.0, sellingPrice = 2999.0, discountPercent = 62,
            rating = 4.0f, reviewCount = 4500,
            imageUrls = listOf("https://images.unsplash.com/photo-1579586337278-3befd40fd17a?w=400&h=400&fit=crop"),
            shopId = "s4", shopName = "ElectroMart", quantity = 50, warranty = "1 Year Noise Warranty", manufacturerDetails = "Noise"
        ),
        Product(
            id = "p15", name = "Sony 65\" Bravia XR OLED", brand = "Sony",
            category = "Televisions",
            description = "Cognitive Processor XR for unmatched realism.",
            specifications = mapOf("Screen Size" to "65 inches", "Panel" to "OLED 4K", "Smart TV" to "Google TV"),
            mrp = 349900.0, sellingPrice = 289900.0, discountPercent = 17,
            rating = 4.9f, reviewCount = 310,
            imageUrls = listOf("https://images.unsplash.com/photo-1593784991095-a205069470b6?w=400&h=400&fit=crop"),
            shopId = "s2", shopName = "TechZone Electronics", quantity = 3, warranty = "2 Years Sony Warranty", manufacturerDetails = "Sony"
        ),
        Product(
            id = "p16", name = "JBL Flip 6", brand = "JBL",
            category = "Audio",
            description = "Portable waterproof bluetooth speaker.",
            specifications = mapOf("Water Resistance" to "IP67", "Battery" to "12 Hours", "Output" to "20W"),
            mrp = 13999.0, sellingPrice = 9999.0, discountPercent = 28,
            rating = 4.6f, reviewCount = 3200,
            imageUrls = listOf("https://images.unsplash.com/photo-1608043152269-423dbba4e7e1?w=400&h=400&fit=crop"),
            shopId = "s2", shopName = "TechZone Electronics", quantity = 40, warranty = "1 Year JBL Warranty", manufacturerDetails = "Harman"
        ),
        Product(
            id = "p17", name = "Logitech G Pro X Superlight", brand = "Logitech",
            category = "Accessories",
            description = "Ultra-lightweight wireless gaming mouse.",
            specifications = mapOf("Weight" to "63g", "Sensor" to "HERO 25K", "Battery" to "70 Hours"),
            mrp = 15995.0, sellingPrice = 12995.0, discountPercent = 18,
            rating = 4.8f, reviewCount = 870,
            imageUrls = listOf("https://images.unsplash.com/photo-1527814050087-379381547949?w=400&h=400&fit=crop"),
            shopId = "s3", shopName = "Gadget Hub", quantity = 25, warranty = "2 Years Logitech Warranty", manufacturerDetails = "Logitech"
        ),
        Product(
            id = "p18", name = "Anker PowerCore 20K", brand = "Anker",
            category = "Accessories",
            description = "High capacity 20000mAh power bank.",
            specifications = mapOf("Capacity" to "20000mAh", "Ports" to "USB-C, USB-A", "Fast Charging" to "20W"),
            mrp = 4999.0, sellingPrice = 2999.0, discountPercent = 40,
            rating = 4.5f, reviewCount = 1200,
            imageUrls = listOf("https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?w=400&h=400&fit=crop"),
            shopId = "s4", shopName = "ElectroMart", quantity = 60, warranty = "18 Months Anker Warranty", manufacturerDetails = "Anker"
        ),
        Product(
            id = "p19", name = "Nikon Z6 II", brand = "Nikon",
            category = "Cameras",
            description = "Versatile full-frame mirrorless camera for photo and video.",
            specifications = mapOf("Sensor" to "24.5MP Full-Frame", "Processor" to "Dual EXPEED 6", "Video" to "4K 60p"),
            mrp = 175995.0, sellingPrice = 159995.0, discountPercent = 9,
            rating = 4.7f, reviewCount = 215,
            imageUrls = listOf("https://images.unsplash.com/photo-1512790182412-b19e6d62bc39?w=400&h=400&fit=crop"),
            shopId = "s3", shopName = "Gadget Hub", quantity = 6, warranty = "2 Years Nikon Warranty", manufacturerDetails = "Nikon"
        ),
        Product(
            id = "p20", name = "Dyson V12 Detect Slim", brand = "Dyson",
            category = "Home Appliances",
            description = "Lightweight cordless vacuum with laser illumination.",
            specifications = mapOf("Weight" to "2.2kg", "Run Time" to "60 Mins", "Bin Volume" to "0.35L"),
            mrp = 55900.0, sellingPrice = 49900.0, discountPercent = 10,
            rating = 4.8f, reviewCount = 540,
            imageUrls = listOf("https://images.unsplash.com/photo-1558317374-067fb5f30001?w=400&h=400&fit=crop"),
            shopId = "s2", shopName = "TechZone Electronics", quantity = 10, warranty = "2 Years Dyson Warranty", manufacturerDetails = "Dyson"
        ),
        Product(
            id = "p21", name = "Philips Digital Air Fryer", brand = "Philips",
            category = "Home Appliances",
            description = "Healthy frying with Rapid Air Technology.",
            specifications = mapOf("Capacity" to "4.1L", "Power" to "1400W", "Presets" to "7"),
            mrp = 11995.0, sellingPrice = 7999.0, discountPercent = 33,
            rating = 4.5f, reviewCount = 2800,
            imageUrls = listOf("https://images.unsplash.com/photo-1628191010210-a59de33e5941?w=400&h=400&fit=crop"),
            shopId = "s2", shopName = "TechZone Electronics", quantity = 25, warranty = "2 Years Philips Warranty", manufacturerDetails = "Philips"
        ),
        Product(
            id = "p22", name = "Google Pixel 8 Pro", brand = "Google",
            category = "Smartphones",
            description = "The ultimate AI smartphone with Pro-level cameras.",
            specifications = mapOf("Display" to "6.7\" Super Actua", "Processor" to "Tensor G3", "RAM" to "12 GB", "Storage" to "256 GB"),
            mrp = 106999.0, sellingPrice = 96999.0, discountPercent = 9,
            rating = 4.6f, reviewCount = 890,
            imageUrls = listOf("https://images.unsplash.com/photo-1696446700622-441ab91936c5?w=400&h=400&fit=crop"),
            shopId = "s1", shopName = "Digital Galaxy Store", quantity = 15, warranty = "1 Year Google Warranty", manufacturerDetails = "Google"
        ),
        Product(
            id = "p23", name = "Samsung Galaxy Tab S9", brand = "Samsung",
            category = "Tablets",
            description = "Premium Android tablet with Dynamic AMOLED 2X.",
            specifications = mapOf("Display" to "11\" Dynamic AMOLED 2X", "Processor" to "Snapdragon 8 Gen 2", "Included" to "S Pen"),
            mrp = 83999.0, sellingPrice = 72999.0, discountPercent = 13,
            rating = 4.7f, reviewCount = 420,
            imageUrls = listOf("https://images.unsplash.com/photo-1542751371-adc38448a05e?w=400&h=400&fit=crop"),
            shopId = "s5", shopName = "Smart World", quantity = 12, warranty = "1 Year Samsung Warranty", manufacturerDetails = "Samsung"
        ),
        Product(
            id = "p24", name = "Xbox Series X", brand = "Microsoft",
            category = "Gaming",
            description = "The fastest, most powerful Xbox ever.",
            specifications = mapOf("CPU" to "8X Cores @ 3.8 GHz", "GPU" to "12 TFLOPS", "Storage" to "1 TB Custom NVME SSD"),
            mrp = 54990.0, sellingPrice = 49990.0, discountPercent = 9,
            rating = 4.8f, reviewCount = 1100,
            imageUrls = listOf("https://images.unsplash.com/photo-1605901309584-818e25960b8f?w=400&h=400&fit=crop"),
            shopId = "s3", shopName = "Gadget Hub", quantity = 8, warranty = "1 Year Microsoft Warranty", manufacturerDetails = "Microsoft"
        ),
        Product(
            id = "p25", name = "OnePlus 12", brand = "OnePlus",
            category = "Smartphones",
            description = "Smooth beyond belief with Snapdragon 8 Gen 3.",
            specifications = mapOf("Display" to "6.82\" AMOLED", "RAM" to "16 GB", "Storage" to "512 GB"),
            mrp = 69999.0, sellingPrice = 64999.0, discountPercent = 7,
            rating = 4.6f, reviewCount = 850,
            imageUrls = listOf("https://images.unsplash.com/photo-1707328956891-fb45f5a57a1b?w=400&h=400&fit=crop"),
            shopId = "s7", shopName = "Mobile Square", quantity = 35, warranty = "1 Year OnePlus Warranty", manufacturerDetails = "OnePlus Technology"
        ),
        Product("p26", "Xiaomi 14 Ultra", "Xiaomi", "Smartphones", "Photography flagship with Leica optics.", mapOf("Display" to "6.73\" AMOLED", "RAM" to "16 GB", "Storage" to "512 GB"), 99999.0, 89999.0, 10, 4.5f, 420, listOf("https://images.unsplash.com/photo-1621330396173-e41b1cafd17f?w=400&h=400&fit=crop"), "s7", "Mobile Square", quantity = 15, warranty = "1 Year Xiaomi Warranty", manufacturerDetails = "Xiaomi Inc."),
        Product("p27", "Vivo X100 Pro", "Vivo", "Smartphones", "Zeiss optics for unmatched portraits.", mapOf("Display" to "6.78\" AMOLED", "RAM" to "16 GB"), 89999.0, 84999.0, 5, 4.4f, 310, listOf("https://images.unsplash.com/photo-1598327105666-5b89351aff97?w=400&h=400&fit=crop"), "s7", "Mobile Square", quantity = 10, warranty = "1 Year Vivo Warranty", manufacturerDetails = "Vivo Mobile"),
        Product("p28", "iQOO 12", "iQOO", "Smartphones", "Monster inside with premium performance.", mapOf("Display" to "6.78\" AMOLED", "Processor" to "Snapdragon 8 Gen 3"), 59999.0, 52999.0, 11, 4.7f, 950, listOf("https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=400&h=400&fit=crop"), "s1", "Digital Galaxy Store", quantity = 40, warranty = "1 Year iQOO Warranty", manufacturerDetails = "iQOO"),
        Product("p29", "Motorola Edge 50 Pro", "Motorola", "Smartphones", "Pantone validated colors and camera.", mapOf("Display" to "6.7\" pOLED", "RAM" to "12 GB"), 35999.0, 31999.0, 11, 4.3f, 210, listOf("https://images.unsplash.com/photo-1546054454-aa26e2b734c7?w=400&h=400&fit=crop"), "s1", "Digital Galaxy Store", quantity = 25, warranty = "1 Year Motorola Warranty", manufacturerDetails = "Lenovo"),
        Product("p30", "Nothing Phone (2)", "Nothing", "Smartphones", "Iconic Glyph interface.", mapOf("Display" to "6.7\" OLED", "Processor" to "Snapdragon 8+ Gen 1"), 49999.0, 44999.0, 10, 4.5f, 650, listOf("https://images.unsplash.com/photo-1678911820864-1dd81cd095e2?w=400&h=400&fit=crop"), "s4", "ElectroMart", quantity = 20, warranty = "1 Year Nothing Warranty", manufacturerDetails = "Nothing Technology"),
        Product("p31", "Realme 12 Pro+", "Realme", "Smartphones", "Luxury watch design with periscope camera.", mapOf("Display" to "6.7\" AMOLED", "RAM" to "8 GB"), 31999.0, 29999.0, 6, 4.2f, 1500, listOf("https://images.unsplash.com/photo-1574944985070-8f3ebc6b79d2?w=400&h=400&fit=crop"), "s4", "ElectroMart", quantity = 60, warranty = "1 Year Realme Warranty", manufacturerDetails = "Realme"),
        
        Product("p32", "Lenovo Legion Pro 5i", "Lenovo", "Laptops", "AI-tuned gaming performance.", mapOf("Display" to "16\" WQXGA 240Hz", "Processor" to "Intel i7", "GPU" to "RTX 4070"), 180000.0, 165000.0, 8, 4.8f, 540, listOf("https://images.unsplash.com/photo-1593640495253-23196b27a87f?w=400&h=400&fit=crop"), "s8", "ComputeTech", quantity = 12, warranty = "1 Year Lenovo Warranty", manufacturerDetails = "Lenovo"),
        Product("p33", "Acer Nitro V", "Acer", "Laptops", "Best value gaming laptop.", mapOf("Display" to "15.6\" FHD 144Hz", "Processor" to "Intel i5", "GPU" to "RTX 4050"), 85000.0, 75000.0, 11, 4.4f, 1200, listOf("https://images.unsplash.com/photo-1603302576837-37561b2e2302?w=400&h=400&fit=crop"), "s8", "ComputeTech", quantity = 30, warranty = "1 Year Acer Warranty", manufacturerDetails = "Acer"),
        Product("p34", "MSI Katana 15", "MSI", "Laptops", "Sharpen your game.", mapOf("Display" to "15.6\" FHD", "Processor" to "Intel i7", "GPU" to "RTX 4060"), 110000.0, 95000.0, 13, 4.5f, 320, listOf("https://images.unsplash.com/photo-1588702545928-8b940e4f24ef?w=400&h=400&fit=crop"), "s8", "ComputeTech", quantity = 15, warranty = "1 Year MSI Warranty", manufacturerDetails = "Micro-Star International"),
        Product("p35", "Apple MacBook Pro 16", "Apple", "Laptops", "M3 Max beast for professionals.", mapOf("Display" to "16.2\" Liquid Retina XDR", "Processor" to "M3 Max", "RAM" to "36GB"), 349900.0, 329900.0, 5, 4.9f, 210, listOf("https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=400&h=400&fit=crop"), "s5", "Smart World", quantity = 5, warranty = "1 Year Apple Warranty", manufacturerDetails = "Apple Inc."),
        Product("p36", "Samsung Galaxy Book4 Pro", "Samsung", "Laptops", "Ultra-thin OLED Intel Evo laptop.", mapOf("Display" to "14\" AMOLED", "Processor" to "Intel Core Ultra 7"), 140000.0, 129000.0, 7, 4.6f, 180, listOf("https://images.unsplash.com/photo-1588702545928-8b940e4f24ef?w=400&h=400&fit=crop"), "s5", "Smart World", quantity = 10, warranty = "1 Year Samsung Warranty", manufacturerDetails = "Samsung"),
        Product("p37", "LG Gram 16", "LG", "Laptops", "Lightest 16-inch laptop in the world.", mapOf("Display" to "16\" WQXGA", "Processor" to "Intel i7", "Weight" to "1.19kg"), 120000.0, 105000.0, 12, 4.7f, 250, listOf("https://images.unsplash.com/photo-1593642532744-d377ab507dc8?w=400&h=400&fit=crop"), "s8", "ComputeTech", quantity = 8, warranty = "1 Year LG Warranty", manufacturerDetails = "LG Electronics"),

        Product("p38", "Apple AirPods Pro 2", "Apple", "Audio", "Magic like you've never heard.", mapOf("Type" to "TWS", "ANC" to "Yes"), 24900.0, 22900.0, 8, 4.8f, 5600, listOf("https://images.unsplash.com/photo-1600294037681-c80b4cb5b434?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 100, warranty = "1 Year Apple Warranty", manufacturerDetails = "Apple Inc."),
        Product("p39", "Samsung Galaxy Buds2 Pro", "Samsung", "Audio", "24-bit Hi-Fi audio.", mapOf("Type" to "TWS", "ANC" to "Yes"), 17999.0, 14999.0, 16, 4.6f, 2300, listOf("https://images.unsplash.com/photo-1505236273191-1dce886b01e9?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 45, warranty = "1 Year Samsung Warranty", manufacturerDetails = "Samsung"),
        Product("p40", "Sennheiser Momentum 4", "Sennheiser", "Audio", "Audiophile-inspired sound.", mapOf("Type" to "Over-Ear Wireless", "Battery" to "60 Hours"), 34990.0, 27990.0, 20, 4.7f, 410, listOf("https://images.unsplash.com/photo-1585298723682-7115561c51b7?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 12, warranty = "2 Years Sennheiser Warranty", manufacturerDetails = "Sennheiser"),
        Product("p41", "Bose QuietComfort Ultra", "Bose", "Audio", "World-class noise cancellation.", mapOf("Type" to "Over-Ear Wireless", "ANC" to "Yes"), 35900.0, 32900.0, 8, 4.8f, 670, listOf("https://images.unsplash.com/photo-1618366712010-f4ae9c647dcb?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 20, warranty = "1 Year Bose Warranty", manufacturerDetails = "Bose Corporation"),
        Product("p42", "OnePlus Buds 3", "OnePlus", "Audio", "Splendid sound, silent world.", mapOf("Type" to "TWS", "ANC" to "49dB"), 5499.0, 4499.0, 18, 4.4f, 1500, listOf("https://images.unsplash.com/photo-1590658268037-6bf12f032f55?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 80, warranty = "1 Year OnePlus Warranty", manufacturerDetails = "OnePlus"),
        Product("p43", "Jabra Elite 8 Active", "Jabra", "Audio", "The world's toughest earbuds.", mapOf("Type" to "TWS", "Durability" to "IP68"), 17999.0, 14999.0, 16, 4.5f, 320, listOf("https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 15, warranty = "2 Years Jabra Warranty", manufacturerDetails = "Jabra"),
        Product("p44", "Marshall Major IV", "Marshall", "Audio", "80+ hours of wireless playtime.", mapOf("Type" to "On-Ear Wireless", "Battery" to "80 Hours"), 14999.0, 11999.0, 20, 4.6f, 890, listOf("https://images.unsplash.com/photo-1546435770-a3e426bf472b?w=400&h=400&fit=crop"), "s6", "Audio Pro", quantity = 25, warranty = "1 Year Marshall Warranty", manufacturerDetails = "Zound Industries")
    )

    val sampleAddresses = listOf(
        com.swastik.app.data.model.Address(
            "a1", "Home", "B-42, Sector 15, Vasant Kunj",
            "New Delhi", "Delhi", "110070", true
        ),
        com.swastik.app.data.model.Address(
            "a2", "Office", "Tower C, 5th Floor, Cyber City",
            "Gurgaon", "Haryana", "122002", false
        )
    )

    val sampleUser = User(
        id = "u1",
        name = "Arjun Mehta",
        email = "arjun.mehta@email.com",
        phone = "9876543210",
        addresses = sampleAddresses
    )

    val sampleOrders = listOf(
        Order(
            id = "ORD-2024-001",
            items = listOf(CartItem(products[0], 1)),
            totalAmount = 144900.0,
            status = OrderStatus.DELIVERED,
            orderDate = "15 Mar 2024",
            deliveryDate = "18 Mar 2024",
            address = sampleAddresses[0],
            paymentMethod = "UPI"
        ),
        Order(
            id = "ORD-2024-002",
            items = listOf(CartItem(products[3], 1), CartItem(products[9], 2)),
            totalAmount = 27588.0,
            status = OrderStatus.SHIPPED,
            orderDate = "20 Mar 2024",
            address = sampleAddresses[0],
            paymentMethod = "Credit Card"
        ),
        Order(
            id = "ORD-2024-003",
            items = listOf(CartItem(products[5], 1)),
            totalAmount = 49990.0,
            status = OrderStatus.CONFIRMED,
            orderDate = "24 Mar 2024",
            address = sampleAddresses[1],
            paymentMethod = "COD"
        )
    )
}
