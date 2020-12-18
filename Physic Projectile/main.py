import math


def max_height():
    v0 = float(input('What is the initial velocity?(m/s^2)'))
    theta = float(input('Angle subject to the horizontal line:(degree) '))
    y0 = float(input('Initial height:(m): '))
    vy = math.sin(math.radians(theta)) * v0
    t = vy / 9.8
    yf = y0 + vy * t + 1 / 2 * 9.8 * t * t
    print(f'Time at the max height {round(t, 2)} s')
    print(f"The max height will be {round(yf, 2)}m")


def final_velocity():
    v0 = float(input('What is the initial velocity?(m/s^2)(upward is negative): '))
    y0 = float(input('Initial height(m): '))
    vf = math.sqrt(v0 * v0 + 2 * 9.8 * y0)
    print(f"The final velocity would be {round(vf, 2)} m/s^2")


def position():
    v0 = float(input('What is the initial velocity?(m/s^2) '))
    theta = float(input('Angle subject to the horizontal line:(degree) '))
    y0 = float(input('Initial height:(m): '))
    x0 = float(input('Initial position x:(m): '))
    vy = math.sin(math.radians(theta)) * v0
    vx = math.cos(math.radians(theta)) * v0
    t = float(input('Time: '))
    yf = y0 + vy * t + 1 / 2 * 9.8 * t * t
    xf = x0 + vx * t + 1 / 2 * 9.8 * t * t
    print(f"Position at time at {round(t, 2)}s = {round(xf, 2)}m")
    print(f"Height at time at {round(t, 2)}s = {round(yf, 2)}m")

def time_of_flight():
    v0 = float(input('What is the initial velocity?(m/s^2) '))
    theta = float(input('Angle subject to the horizontal line:(degree) '))
    y0 = float(input('Initial height:(m): '))
    vy = math.sin(math.radians(theta)) * v0
    t = (-vy-math.sqrt(vy*vy+2*9.8*y0))/-9.8
    print(f"Time of flight = {round(t, 3)}s")

def final_position():
    v0 = float(input('What is the initial velocity?(m/s^2) '))
    theta = float(input('Angle subject to the horizontal line:(degree) '))
    y0 = float(input('Initial height:(m): '))
    x0 = float(input('Initial position x:(m): '))
    vy = math.sin(math.radians(theta)) * v0
    vx = math.cos(math.radians(theta)) * v0
    t = (-vy-math.sqrt(vy*vy+2*9.8*y0))/-9.8
    yf = y0 + vy * t + 1 / 2 * 9.8 * t * t
    xf = x0 + vx * t + 1 / 2 * 9.8 * t * t
    print(f"Final x position = {round(xf, 2)}m")









def user_choice(choice):
    if choice == 1:
        max_height()
    elif choice == 2:
        final_velocity()
    elif choice == 3:
        position()
    elif choice == 4:
        time_of_flight()
    elif choice == 5:
        final_position()


def menu():
    print('Welcome to projectile calculator!')
    print('1. Calculate Y max height.')
    print('2. Final Velocity.')
    print('3. X and Y postion at time t.')
    print('4. Time of flight.')
    print('5. Final position.')
    print('0. Exit')


menu()
choice = int(input('Let me know what do you want to calculate!\nYour choice: '))
user_choice(choice)
while choice != 0:
    print("\n\n")
    menu()
    choice = int(input('Let me know what do you want to calculate!\nYour choice: '))
    user_choice(choice)

print("Thanks for using projectile calculator")
