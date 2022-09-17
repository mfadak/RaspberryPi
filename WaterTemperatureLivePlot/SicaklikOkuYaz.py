import csv
import time
from w1thermsensor import W1ThermSensor

index = 1
sicaklik = 25

sutunlar = ["kayit", "sicaklik"]

with open('veri.csv', 'w') as csvDosya:
    csvYaz = csv.DictWriter(csvDosya, fieldnames=sutunlar)
    csvYaz.writeheader()
    
sensor = W1ThermSensor()
while True:
    sicaklik = sensor.get_temperature()
    dosya = open('veri.csv', 'a')
    csvYaz = csv.DictWriter(dosya, fieldnames=sutunlar)
    bilgi = {
        "kayit": index,
        "sicaklik": sicaklik
    }

    csvYaz.writerow(bilgi)

    index += 1
    dosya.close()
    time.sleep(1)
