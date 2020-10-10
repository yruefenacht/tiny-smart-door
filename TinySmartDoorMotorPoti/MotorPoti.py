from tinkerforge.ip_connection import IPConnection
from tinkerforge.bricklet_motorized_linear_poti import BrickletMotorizedLinearPoti

class MotorPoti:

	UID = "DCx"
	HOST = "localhost"
	PORT = 4223

	def __init__(self):
		self.ipconnection = IPConnection()
		self.mlp = BrickletMotorizedLinearPoti(UID, self.ipconnection)
		self.ipconnection.connect(HOST, PORT)
		self.mlp.register_callback(self.mlp.CALLBACK_POSITION_REACHED, self.position_reached)
		

	def position_reached(self, position):
		print("Position: ", position)


	def open_door(self, callback):
		self.mlp.set_motor_position(0, self.mlp.DRIVE_MODE_SMOOTH, False)
		callback("opened")
		print("Opened door")


	def close_door(self, callback):
		self.mlp.set_motor_position(100, self.mlp.DRIVE_MODE_SMOOTH, False)
		callback("closed")
		print("Closed door")
		