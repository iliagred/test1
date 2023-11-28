List<String> sortedPlugins = new ArrayList<String>(Jenkins.instance.pluginManager.plugins)

sortedPlugins = sortedPlugins.sort{x -> x.displayName.toLowerCase()}

for (plugin in sortedPlugins){
    println(plugin.displayName)
}
