import MotorPotiService as mpService
import config

if __name__ == "__main__":
    motorPotiService = mpService.MotorPotiService(
        config.broker_url,
        config.broker_port,
        config.broker_username,
        config.broker_password)