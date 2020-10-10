import DispatcherService as dpService
import config

if __name__ == "__main__":
    dispatcherService = dpService.DispatcherService(
        config.broker_url,
        config.broker_port,
        config.broker_username,
        config.broker_password)