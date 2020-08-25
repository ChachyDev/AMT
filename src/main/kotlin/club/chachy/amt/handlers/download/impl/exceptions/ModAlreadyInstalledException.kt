package club.chachy.amt.handlers.download.impl.exceptions

class ModAlreadyInstalledException(mod: String) : IllegalStateException("$mod is already installed")