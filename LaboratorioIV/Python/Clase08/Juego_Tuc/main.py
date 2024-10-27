import sys
import pygame
import random
import os

from personaje import *
from constantes import SCREEN_WIDTH, SCREEN_HEIGTH, ASSETS_PATH, IMPERIAL_MARCH_PATH, START_IMAGE_PATH, ESTRELLA_PATH, FONDO1_PATH
from explosiones import Explosion

def mostrar_pantalla_inicio(screen):
    imagen_inicio = pygame.image.load(START_IMAGE_PATH)
    imagen_inicio = pygame.transform.scale(imagen_inicio, (SCREEN_WIDTH, SCREEN_HEIGTH))
    screen.blit(imagen_inicio, (0, 0))
    pygame.display.flip()

    pygame.mixer.music.load(IMPERIAL_MARCH_PATH)
    pygame.mixer.music.play()

    while pygame.mixer.music.get_busy():
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
        screen.blit(imagen_inicio, (0, 0))
        pygame.display.flip()

def main():
    pygame.init()
    screen = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGTH))
    pygame.display.set_caption('Amenaza Fantasma')

    icon = pygame.image.load(f'{ASSETS_PATH}/images/fondo001.jpeg')
    pygame.display.set_icon(icon)

    fondo = pygame.image.load(f'{ASSETS_PATH}/images/fondo001.jpeg')
    fondo = pygame.transform.scale(fondo, (SCREEN_WIDTH, SCREEN_HEIGTH))

    fondo1 = pygame.image.load(FONDO1_PATH)
    fondo1 = pygame.transform.scale(fondo1, (SCREEN_WIDTH, SCREEN_HEIGTH))

    estrella = pygame.image.load(ESTRELLA_PATH)
    estrella = pygame.transform.scale(estrella, (SCREEN_WIDTH, SCREEN_HEIGTH))

    sonido_laser = pygame.mixer.Sound(f'{ASSETS_PATH}/sounds/explosion.wav')

    personaje = Personaje(SCREEN_WIDTH // 2, SCREEN_HEIGTH // 2)
    enemigos = []
    explosiones = []
    puntos = 0
    nivel = 1

    clock = pygame.time.Clock()
    running = True

    fondo_actual = fondo

    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()

        keys = pygame.key.get_pressed()
        dx, dy = 0, 0

        if keys[pygame.K_LEFT]:
            dx = -5
        if keys[pygame.K_RIGHT]:
            dx = 5
        if keys[pygame.K_UP]:
            dy = -5
        if keys[pygame.K_DOWN]:
            dy = 5

        personaje.mover(dx, dy)

        if keys[pygame.K_SPACE]:
            personaje.lanzar_laser()
            sonido_laser.play()

        for enemigo in enemigos[:]:
            enemigo.mover()
            if enemigo.rect.top > SCREEN_HEIGTH:
                enemigos.remove(enemigo)

        for laser in personaje.lasers[:]:
            for enemigo in enemigos[:]:
                if enemigo.rect.colliderect(laser.rect):
                    explosiones.append(Explosion(enemigo.rect.centerx, enemigo.rect.centery))
                    enemigos.remove(enemigo)
                    personaje.lasers.remove(laser)
                    sonido_laser.play()
                    puntos += 10
                    break
                if enemigo.rect.colliderect(personaje.shape):
                    if not personaje.recibir_daño():
                        running = False

        if random.random() < 0.02:
            x = random.randint(0, SCREEN_WIDTH - 50)
            enemigo = Enemigo(x, 0)
            enemigos.append(enemigo)

        explosiones = [explosion for explosion in explosiones if explosion.actualizar()]

        if puntos >= 250:
            if fondo_actual == fondo:
                fondo_actual = estrella
            else:
                fondo_actual = fondo1
                puntos = 0
                nivel += 1

        screen.blit(fondo_actual, (0, 0))
        personaje.dibujar(screen)
        for enemigo in enemigos:
            enemigo.dibujar(screen)
        for explosion in explosiones:
            explosion.dibujar(screen)

        font = pygame.font.Font(None, 36)
        texto_puntos = font.render(f"Puntos: {puntos}", True, (255, 255, 255))
        texto_nivel = font.render(f"Nivel: {nivel}", True, (255, 255, 255))
        screen.blit(texto_puntos, (10, 50))
        screen.blit(texto_nivel, (10, 90))

        pygame.display.flip()
        clock.tick(60)

    # Pantalla de GAME OVER
    screen.fill((0, 0, 0))
    texto_game_over = font.render("GAME OVER", True, (255, 0, 0))
    texto_mensaje_final = font.render("Presiona R para reiniciar", True, (255, 255, 255))

    screen.blit(texto_game_over, (SCREEN_WIDTH // 2 - texto_game_over.get_width() // 2, SCREEN_HEIGTH // 2))
    screen.blit(texto_mensaje_final, (SCREEN_WIDTH // 2 - texto_mensaje_final.get_width() // 2, SCREEN_HEIGTH // 2 + 50))
    pygame.display.flip()

    pygame.time.wait(5000)
    pygame.quit()
    sys.exit()

if __name__ == '__main__':
    main()
