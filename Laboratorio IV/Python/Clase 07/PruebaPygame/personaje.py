import pygame


pygame.init()

#configuracion de pantalla
screen = pygame.display.set_mode((800, 600))
clock = pygame.time.Clock()
running = True
dt = 0

#posicion del jugador
player_pos = pygame.Vector2(screen.get_width() / 2, screen.get_height() / 2)

#velocidad del jugador
player_speed = 5

#bucle principal
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False


    #detectar las teclas para presionar
    keys = pygame.key.get_pressed()

    #movimiento del jugador
    if keys[pygame.K_LEFT]:     #se presiona la tecla izquierda
        player_pos.x -= player_speed
    if keys[pygame.K_RIGHT]:    #se presiona la tecla derecha
        player_pos.x += player_speed
    if keys[pygame.K_DOWN]:     #se presiona la tecla hacia abajo
        player_pos.y += player_speed
    if keys[pygame.K_UP]:   #se presiona la tecla hacia arriba
        player_pos.y -= player_speed
    
    #limpiar la pantalla
    screen.fill('green')

    #dibujar el jugador (en este caso es un circulo rojo)
    pygame.draw.circle(screen,(255, 0, 0), (int(player_pos.x), int(player_pos.y)), 10)

    #actualizar pantalla
    pygame.display.flip()

    #control de velocidad del jugador (fps)
    clock.tick(60)

pygame.quit() #aqui termina nuestro programa