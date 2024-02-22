from typing import List, Dict
import matplotlib.pyplot as plt


def plot_data(data: Dict[str, List[float]], x_values: List[float], title: str):
    for key, values in data.items():
        values = [v / 1000.0 for v in values]
        plt.plot(x_values, values, label=key)

    plt.xlabel("n")
    plt.ylabel("t (s)")
    plt.legend()
    plt.title(title)
    plt.show()

array_list_dat = [22, 68, 201, 395, 828]
linked_list_dat = [91, 698, 2670, 6973, 12073]
Effict_Array_list_dat = [1 , 1 ,1 , 2, 3]
Effict_Linked_list_dat =[0, 0 , 0 ,0 , 1, ]

x_values = [10000, 20000, 30000, 40000, 50000]


data = {
    "ArrayList": array_list_dat,
    "LinkedList": linked_list_dat,
    "Effict_Array_list_dat" : Effict_Array_list_dat,
    "Effict_Linked_list_dat": Effict_Linked_list_dat,
    }
plot_data(data, x_values, "ArrayList vs LinkedList")