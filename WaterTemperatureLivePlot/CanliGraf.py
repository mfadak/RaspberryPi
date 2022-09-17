from itertools import count
import pandas as pd
import matplotlib.pyplot as plt
from matplotlib.animation import FuncAnimation

plt.style.use('fivethirtyeight')

def animate(i):
    veri = pd.read_csv('veri.csv')
    kayit = veri['kayit']
    sicaklik = veri['sicaklik']

    plt.cla()

    plt.plot(kayit, sicaklik, label='Sıcaklık')

    plt.legend(loc='upper left')
    plt.tight_layout()


ani = FuncAnimation(plt.gcf(), animate, interval=1000)

plt.tight_layout()
plt.show()
