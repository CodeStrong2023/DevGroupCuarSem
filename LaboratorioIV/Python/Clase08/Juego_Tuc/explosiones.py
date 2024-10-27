import pygame
import os

from constantes import ASSETS_PATH

class Explosion(pygame.sprite.Sprite):
    def __init__(self, x, y):
        super().__init__()
        self.image = [pygame.image.load(f'{ASSETS_PATH}/images/regularExplosion{i:02d}.png').convert_alpha() 
                      for i in range(9)]  # Cargar imágenes con transparencia
        self.index = 0  # Índice para controlar la animación
        self.rect = self.image[0].get_rect(center=(x, y))  # Obtener el rectángulo de la primera imagen
        self.frame_rate = 0  # Control del tiempo entre frames
        self.max_frames = len(self.image)  # Número total de frames

    def actualizar(self):
        """Controla la animación de la explosión."""
        self.frame_rate += 1
        if self.frame_rate % 5 == 0:  # Cambia de frame cada 5 ciclos
            self.index += 1  # Avanza al siguiente frame
            if self.index < self.max_frames:
                self.image_actual = self.image[self.index]  # Cambia la imagen actual
            else:
                self.kill()  # Elimina la explosión cuando termina la animación

    def dibujar(self, screen):
        """Dibuja la explosión en pantalla."""
        screen.blit(self.image[self.index], self.rect)
